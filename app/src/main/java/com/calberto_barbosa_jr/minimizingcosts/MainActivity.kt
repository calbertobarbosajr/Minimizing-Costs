package com.calberto_barbosa_jr.minimizingcosts

import android.os.Bundle
import android.view.ViewGroup
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.calberto_barbosa_jr.minimizingcosts.ui.theme.MinimizingCostsTheme
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.viewmodel.compose.viewModel
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MinimizingCostsTheme {
                BusinessApp()
            }
        }
    }
}

@Composable
fun BusinessApp(viewModel: BusinessViewModel = viewModel()) {
    val clientsAI by viewModel.clientsAI.observeAsState(0)
    val clientsNoAI by viewModel.clientsNoAI.observeAsState(0)
    val costsAI by viewModel.costsAI.observeAsState(0.0)
    val costsNoAI by viewModel.costsNoAI.observeAsState(0.0)
    val revenueAI by viewModel.revenueAI.observeAsState(0.0)
    val revenueNoAI by viewModel.revenueNoAI.observeAsState(0.0)
    val gameOver by viewModel.gameOver.observeAsState(false)

    val chartData = remember { mutableStateListOf<Entry>() }
    LaunchedEffect(revenueAI, costsAI) {
        chartData.add(Entry(chartData.size.toFloat(), revenueAI.toFloat() - costsAI.toFloat()))
    }

    BusinessScreen(
        clientsAI = clientsAI,
        clientsNoAI = clientsNoAI,
        costsAI = costsAI,
        costsNoAI = costsNoAI,
        revenueAI = revenueAI,
        revenueNoAI = revenueNoAI,
        gameOver = gameOver,
        onUpdateClick = {
            viewModel.updateEnvironment(produceAI = 100, produceNoAI = 80)
        },
        onResetClick = {
            viewModel.resetEnvironment()
        },
        chartData = chartData
    )
}

@Composable
fun BusinessScreen(
    clientsAI: Int,
    clientsNoAI: Int,
    costsAI: Double,
    costsNoAI: Double,
    revenueAI: Double,
    revenueNoAI: Double,
    gameOver: Boolean,
    onUpdateClick: () -> Unit,
    onResetClick: () -> Unit,
    chartData: List<Entry>
) {
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text(text = "Clientes (com IA): $clientsAI", style = MaterialTheme.typography.bodyMedium)
        Text(text = "Clientes (sem IA): $clientsNoAI", style = MaterialTheme.typography.bodyMedium)
        Spacer(modifier = Modifier.height(8.dp))

        Text(text = "Custos Totais (com IA): $costsAI", style = MaterialTheme.typography.bodyMedium)
        Text(text = "Custos Totais (sem IA): $costsNoAI", style = MaterialTheme.typography.bodyMedium)
        Spacer(modifier = Modifier.height(8.dp))

        Text(text = "Receita Total (com IA): $revenueAI", style = MaterialTheme.typography.bodyMedium)
        Text(text = "Receita Total (sem IA): $revenueNoAI", style = MaterialTheme.typography.bodyMedium)
        Spacer(modifier = Modifier.height(8.dp))

        // Botões de atualização e reset
        Button(onClick = onUpdateClick) {
            Text("Atualizar Negócio")
        }
        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = onResetClick) {
            Text("Resetar Ambiente")
        }

        if (gameOver) {
            Text("Fim do Jogo!", style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.error)
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Gráfico de Receita e Custos
        LineChartView(chartData)
    }
}

@Composable
fun LineChartView(chartData: List<Entry>) {
    AndroidView(factory = { context ->
        LineChart(context).apply {
            layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 400)
        }
    }, update = { chart ->
        val dataSet = LineDataSet(chartData, "Lucro (Receita - Custos)")
        dataSet.color = android.graphics.Color.GREEN
        dataSet.valueTextColor = android.graphics.Color.BLACK
        dataSet.setDrawValues(true)

        val lineData = LineData(dataSet)
        chart.data = lineData
        chart.invalidate() // Redesenhar o gráfico
    })
}