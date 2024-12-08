package com.calberto_barbosa_jr.minimizingcosts

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class BusinessViewModel : ViewModel() {

    private val environment = BusinessEnvironment()

    // LiveData para observar o estado do ambiente
    private val _clientsAI = MutableLiveData<Int>()
    val clientsAI: LiveData<Int> = _clientsAI

    private val _clientsNoAI = MutableLiveData<Int>()
    val clientsNoAI: LiveData<Int> = _clientsNoAI

    private val _costsAI = MutableLiveData<Double>()
    val costsAI: LiveData<Double> = _costsAI

    private val _costsNoAI = MutableLiveData<Double>()
    val costsNoAI: LiveData<Double> = _costsNoAI

    private val _revenueAI = MutableLiveData<Double>()
    val revenueAI: LiveData<Double> = _revenueAI

    private val _revenueNoAI = MutableLiveData<Double>()
    val revenueNoAI: LiveData<Double> = _revenueNoAI

    private val _inventory = MutableLiveData<Int>()
    val inventory: LiveData<Int> = _inventory  // Expondo o estoque

    private val _gameOver = MutableLiveData<Boolean>()
    val gameOver: LiveData<Boolean> = _gameOver

    init {
        resetEnvironment()
    }

    fun updateEnvironment(produceAI: Int, produceNoAI: Int) {
        val (stateAI, rewardAI, gameOver) = environment.updateEnvironment(produceAI, produceNoAI)

        _clientsAI.value = environment.currentClients
        _costsAI.value = environment.totalCostsAI
        _revenueAI.value = environment.totalRevenueAI
        _inventory.value = environment.inventory  // Atualizando o estoque

        _clientsNoAI.value = environment.currentClients
        _costsNoAI.value = environment.totalCostsNoAI
        _revenueNoAI.value = environment.totalRevenueNoAI

        _gameOver.value = gameOver
    }

    fun resetEnvironment() {
        environment.reset(newClients = 100, newInventory = 500)
        updateEnvironment(produceAI = 100, produceNoAI = 80)
    }

    fun setSellingPrice(price: Double) {
        environment.sellingPricePerProduct = price
    }

    fun setProductionCost(cost: Double) {
        environment.variableCostPerProduct = cost
    }

    fun setReabastecimentoQuantidade(quantidade: Int) {
        environment.reabastecimentoQuantidade = quantidade
    }

    fun setImpactoPromocao(impacto: Int) {
        environment.maxChangeInClients = impacto
    }
}
