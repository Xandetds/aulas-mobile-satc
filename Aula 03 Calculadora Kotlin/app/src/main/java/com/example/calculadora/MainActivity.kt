package com.example.calculadora

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.calculadora.ui.theme.CalculadoraTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CalculadoraTheme {
                TelaCalculadora()
            }
        }
    }
}

@Composable
fun TelaCalculadora() {
    val corVerdeBrasil = Color(0xFF009C3B)
    val corAmarelaBrasil = Color(0xFFFFDF00)

    var numeroAtual by remember { mutableStateOf("0") }
    var numeroAnterior by remember { mutableStateOf("") }
    var operador by remember { mutableStateOf("") }

    var aguardandoNovoNumero by remember { mutableStateOf(false) }

    fun aoClicarNumero(numeroDigitado: String) {
        if (aguardandoNovoNumero) {
            numeroAtual = numeroDigitado
            aguardandoNovoNumero = false
        } else if (numeroAtual == "0") {
            numeroAtual = numeroDigitado
        } else {
            numeroAtual += numeroDigitado
        }
    }

    fun aoClicarOperador(op: String) {
        numeroAnterior = numeroAtual
        operador = op
        aguardandoNovoNumero = true
    }

    fun calcularResultado() {
        val num1 = numeroAnterior.toDoubleOrNull() ?: 0.0
        val num2 = numeroAtual.toDoubleOrNull() ?: 0.0
        var resultado = 0.0

        when (operador) {
            "+" -> resultado = num1 + num2
            "-" -> resultado = num1 - num2
            "*" -> resultado = num1 * num2
            "/" -> if (num2 != 0.0) resultado = num1 / num2
        }

        numeroAtual = if (resultado % 1.0 == 0.0) {
            resultado.toInt().toString()
        } else {
            resultado.toString()
        }

        operador = ""
        numeroAnterior = ""

        aguardandoNovoNumero = true
    }

    fun limpar() {
        numeroAtual = "0"
        numeroAnterior = ""
        operador = ""
        aguardandoNovoNumero = false
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(corVerdeBrasil)
            .padding(16.dp),
        verticalArrangement = Arrangement.Bottom
    ) {
        Text(
            text = numeroAtual,
            fontSize = 64.sp,
            textAlign = TextAlign.End,
            color = Color.White,
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Blue)
                .padding(16.dp)
                .padding(bottom = 32.dp)
        )

        Row(
            modifier = Modifier.fillMaxWidth().weight(1f),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Button(onClick = { aoClicarNumero("7") }, modifier = Modifier.weight(1f).fillMaxHeight(), colors = ButtonDefaults.buttonColors(containerColor = corAmarelaBrasil)) { Text("7", fontSize = 24.sp, color = Color.Black) }
            Button(onClick = { aoClicarNumero("8") }, modifier = Modifier.weight(1f).fillMaxHeight(), colors = ButtonDefaults.buttonColors(containerColor = corAmarelaBrasil)) { Text("8", fontSize = 24.sp, color = Color.Black) }
            Button(onClick = { aoClicarNumero("9") }, modifier = Modifier.weight(1f).fillMaxHeight(), colors = ButtonDefaults.buttonColors(containerColor = corAmarelaBrasil)) { Text("9", fontSize = 24.sp, color = Color.Black) }
            Button(onClick = { aoClicarOperador("/") }, modifier = Modifier.weight(1f).fillMaxHeight(), colors = ButtonDefaults.buttonColors(containerColor = corAmarelaBrasil)) { Text("/", fontSize = 24.sp, color = Color.Black) }
        }

        Row(
            modifier = Modifier.fillMaxWidth().weight(1f).padding(top = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Button(onClick = { aoClicarNumero("4") }, modifier = Modifier.weight(1f).fillMaxHeight(), colors = ButtonDefaults.buttonColors(containerColor = corAmarelaBrasil)) { Text("4", fontSize = 24.sp, color = Color.Black) }
            Button(onClick = { aoClicarNumero("5") }, modifier = Modifier.weight(1f).fillMaxHeight(), colors = ButtonDefaults.buttonColors(containerColor = corAmarelaBrasil)) { Text("5", fontSize = 24.sp, color = Color.Black) }
            Button(onClick = { aoClicarNumero("6") }, modifier = Modifier.weight(1f).fillMaxHeight(), colors = ButtonDefaults.buttonColors(containerColor = corAmarelaBrasil)) { Text("6", fontSize = 24.sp, color = Color.Black) }
            Button(onClick = { aoClicarOperador("*") }, modifier = Modifier.weight(1f).fillMaxHeight(), colors = ButtonDefaults.buttonColors(containerColor = corAmarelaBrasil)) { Text("*", fontSize = 24.sp, color = Color.Black) }
        }

        Row(
            modifier = Modifier.fillMaxWidth().weight(1f).padding(top = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Button(onClick = { aoClicarNumero("1") }, modifier = Modifier.weight(1f).fillMaxHeight(), colors = ButtonDefaults.buttonColors(containerColor = corAmarelaBrasil)) { Text("1", fontSize = 24.sp, color = Color.Black) }
            Button(onClick = { aoClicarNumero("2") }, modifier = Modifier.weight(1f).fillMaxHeight(), colors = ButtonDefaults.buttonColors(containerColor = corAmarelaBrasil)) { Text("2", fontSize = 24.sp, color = Color.Black) }
            Button(onClick = { aoClicarNumero("3") }, modifier = Modifier.weight(1f).fillMaxHeight(), colors = ButtonDefaults.buttonColors(containerColor = corAmarelaBrasil)) { Text("3", fontSize = 24.sp, color = Color.Black) }
            Button(onClick = { aoClicarOperador("-") }, modifier = Modifier.weight(1f).fillMaxHeight(), colors = ButtonDefaults.buttonColors(containerColor = corAmarelaBrasil)) { Text("-", fontSize = 24.sp, color = Color.Black) }
        }

        Row(
            modifier = Modifier.fillMaxWidth().weight(1f).padding(top = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Button(onClick = { aoClicarNumero("0") }, modifier = Modifier.weight(1f).fillMaxHeight(), colors = ButtonDefaults.buttonColors(containerColor = corAmarelaBrasil)) { Text("0", fontSize = 24.sp, color = Color.Black) }
            Button(onClick = { limpar() }, modifier = Modifier.weight(1f).fillMaxHeight(), colors = ButtonDefaults.buttonColors(containerColor = corAmarelaBrasil)) { Text("C", fontSize = 24.sp, color = Color.Black) }
            Button(onClick = { calcularResultado() }, modifier = Modifier.weight(1f).fillMaxHeight(), colors = ButtonDefaults.buttonColors(containerColor = corAmarelaBrasil)) { Text("=", fontSize = 24.sp, color = Color.Black) }
            Button(onClick = { aoClicarOperador("+") }, modifier = Modifier.weight(1f).fillMaxHeight(), colors = ButtonDefaults.buttonColors(containerColor = corAmarelaBrasil)) { Text("+", fontSize = 24.sp, color = Color.Black) }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCalculadora() {
    CalculadoraTheme {
        TelaCalculadora()
    }
}