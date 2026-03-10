package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                TelaHome()
            }
        }
    }
}

@Composable
fun TelaHome() {
    var jogadaSorteada by remember { mutableStateOf("") }

    fun sortearJogada() {
        val opcoes = listOf("pedra", "papel", "tesoura")
        jogadaSorteada = opcoes.random()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Text(
            text = "Sorteador de Jogada",
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold
        )

        val imagemResId = when (jogadaSorteada) {
            "pedra" -> R.drawable.pedra
            "papel" -> R.drawable.papel
            "tesoura" -> R.drawable.tesoura
            else -> null
        }

        if (imagemResId != null) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Image(
                    painter = painterResource(id = imagemResId),
                    contentDescription = "Imagem sorteada: $jogadaSorteada",
                    modifier = Modifier.size(200.dp)
                )

                Text(
                    text = jogadaSorteada.replaceFirstChar { it.uppercase() },
                    fontSize = 24.sp,
                    modifier = Modifier.padding(top = 16.dp)
                )
            }
        } else {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Spacer(modifier = Modifier.size(200.dp))
                Text(
                    text = "Clique abaixo para sortear!",
                    fontSize = 20.sp
                )
            }
        }

        Button(onClick = { sortearJogada() }) {
            Text("Sortear", fontSize = 18.sp)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewHome(){
    MyApplicationTheme {
        TelaHome()
    }
}