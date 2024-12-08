package com.calberto_barbosa_jr.minimizingcosts

import kotlin.random.Random

class BusinessEnvironment(
    var fixedCosts: Double = 10000.0,
    var variableCostPerProduct: Double = 20.0,
    var sellingPricePerProduct: Double = 50.0,
    var initialClients: Int = 100,
    var initialInventory: Int = 500,
    var promotionalCost: Double = 500.0,
    var reabastecimentoQuantidade: Int = 50  // Quantidade de reabastecimento definida dinamicamente
) {
    private val minClients = 50
    private val maxClients = 500
    var maxChangeInClients = 50
    var currentClients = initialClients
    var inventory = initialInventory
    var productionCost = 0.0
    var totalCostsAI = 0.0
    var totalCostsNoAI = 0.0
    var totalRevenueAI = 0.0
    var totalRevenueNoAI = 0.0
    var rewardAI = 0.0
    var rewardNoAI = 0.0
    var gameOver = false

    // Variáveis para Thompson Sampling
    var successPromotions = 1
    var failuresPromotions = 1

    // Função para atualizar o ambiente (com e sem IA)
    fun updateEnvironment(produceAI: Int, produceNoAI: Int): Triple<Array<Double>, Double, Boolean> {
        val promotionalActionAI = decidePromotionWithThompsonSampling()

        val resultAI = updateWithAI(produceAI, promotionalActionAI)
        val (clientsAI, costsAI, inventoryAI) = resultAI

        val promotionalActionNoAI = false
        val resultNoAI = updateWithoutAI(produceNoAI, promotionalActionNoAI)
        val (clientsNoAI, costsNoAI, inventoryNoAI) = resultNoAI

        rewardAI = totalRevenueAI - totalCostsAI
        rewardNoAI = totalRevenueNoAI - totalCostsNoAI

        updateThompsonSampling(promotionalActionAI, clientsAI)

        val scaledClientsAI = (clientsAI - minClients) / (maxClients - minClients).toDouble()
        val scaledInventoryAI = inventoryAI / 1000.0
        val scaledCostsAI = costsAI / (fixedCosts + maxClients * variableCostPerProduct)
        val nextStateAI = arrayOf(scaledClientsAI, scaledCostsAI, scaledInventoryAI)

        // Verificação para reabastecimento automático quando o estoque estiver baixo
        if (inventoryAI < 20) {
            reabastecerEstoqueAutomaticamente()
        }

        return Triple(nextStateAI, rewardAI, gameOver)
    }

    // Ajustar produção, evitar estoque negativo, e ajustar custos
    private fun updateWithAI(produce: Int, promotionalAction: Boolean): Triple<Int, Double, Int> {
        // Produzir sem exceder a demanda
        productionCost = produce * variableCostPerProduct
        inventory += produce

        // Garantir que vendemos apenas o estoque disponível
        val productsSoldAI = currentClients.coerceAtMost(inventory)
        val revenueAI = productsSoldAI * sellingPricePerProduct
        val variableCostsAI = productsSoldAI * variableCostPerProduct
        totalCostsAI = fixedCosts + productionCost + variableCostsAI
        totalRevenueAI = revenueAI

        // Reduzir o estoque vendido
        inventory -= productsSoldAI

        // Promoção com IA ajustada para não permitir perdas excessivas
        if (promotionalAction) {
            currentClients += (10..maxChangeInClients).random()
        } else {
            currentClients -= (3..5).random()  // Reduzir clientes muito lentamente
        }

        currentClients = currentClients.coerceIn(minClients, maxClients)

        return Triple(currentClients, totalCostsAI, inventory)
    }

    // Função para reabastecer o estoque automaticamente
    private fun reabastecerEstoqueAutomaticamente() {
        inventory += reabastecimentoQuantidade
        productionCost += reabastecimentoQuantidade * variableCostPerProduct
        println("Reabastecimento automático realizado! Quantidade: $reabastecimentoQuantidade")
    }

    private fun updateWithoutAI(produce: Int, promotionalAction: Boolean): Triple<Int, Double, Int> {
        var currentClientsNoAI = currentClients
        inventory += produce
        val productsSoldNoAI = currentClientsNoAI.coerceAtMost(inventory)
        val revenueNoAI = productsSoldNoAI * sellingPricePerProduct
        val variableCostsNoAI = productsSoldNoAI * variableCostPerProduct
        totalCostsNoAI = fixedCosts + (produce * variableCostPerProduct) + variableCostsNoAI
        totalRevenueNoAI = revenueNoAI
        inventory -= productsSoldNoAI

        // Ajustar a redução de clientes no cenário sem IA para ser menos agressiva
        if (promotionalAction) {
            currentClientsNoAI += 20
        } else {
            currentClientsNoAI -= (2..4).random()  // Reduzir clientes muito suavemente
        }

        currentClientsNoAI = currentClientsNoAI.coerceIn(minClients, maxClients)

        return Triple(currentClientsNoAI, totalCostsNoAI, inventory)
    }

    private fun updateThompsonSampling(promotionalAction: Boolean, clientsAfterPromotion: Int) {
        if (promotionalAction) {
            if (clientsAfterPromotion > currentClients) {
                successPromotions += 1
            } else {
                failuresPromotions += 1
            }
        }
    }

    private fun beta(successes: Int, failures: Int): Double {
        val alpha = successes.toDouble()
        val beta = failures.toDouble()
        val numerator = Random.nextDouble(0.0, 1.0)
        val denominator = (alpha + beta).toDouble()
        return numerator / denominator
    }

    fun reset(newClients: Int, newInventory: Int) {
        currentClients = newClients
        inventory = newInventory
        totalCostsAI = 0.0
        totalCostsNoAI = 0.0
        totalRevenueAI = 0.0
        totalRevenueNoAI = 0.0
        rewardAI = 0.0
        rewardNoAI = 0.0
        gameOver = false
        successPromotions = 1
        failuresPromotions = 1
    }

    fun observe(): Triple<Array<Double>, Double, Boolean> {
        val scaledClients = (currentClients - minClients) / (maxClients - minClients).toDouble()
        val scaledInventory = inventory / 1000.0
        val scaledCosts = totalCostsAI / (fixedCosts + maxClients * variableCostPerProduct)
        val currentState = arrayOf(scaledClients, scaledCosts, scaledInventory)
        return Triple(currentState, rewardAI, gameOver)
    }

    private fun decidePromotionWithThompsonSampling(): Boolean {
        val betaSample = Random.nextDouble(beta(successPromotions, failuresPromotions))
        return betaSample > 0.5
    }
}
