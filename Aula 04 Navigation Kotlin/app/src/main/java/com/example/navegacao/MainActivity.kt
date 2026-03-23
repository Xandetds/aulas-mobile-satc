package com.example.navegacao

import android.media.MediaPlayer
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navigationController = rememberNavController()

            NavHost(
                navController = navigationController,
                startDestination = "home"
            ) {
                composable("home") {
                    TelaHome(
                        onClickIniciar = { navigationController.navigate("pista1") }
                    )
                }

                composable("pista1") {
                    TelaPista(
                        charada = "Pista 1: Qual o melhor time do mundo?",
                        onClickProxima = { navigationController.navigate("pista2") },
                        onClickVoltar = { navigationController.popBackStack() }
                    )
                }

                composable("pista2") {
                    TelaPista(
                        charada = "Pista 2: Quem é o verdadeiro Gigante da Colina?",
                        onClickProxima = { navigationController.navigate("pista3") },
                        onClickVoltar = { navigationController.popBackStack() }
                    )
                }

                composable("pista3") {
                    TelaPista(
                        charada = "Pista 3: Qual é o time da virada e do amor?",
                        onClickProxima = { navigationController.navigate("tesouro") },
                        onClickVoltar = { navigationController.popBackStack() }
                    )
                }

                composable("tesouro") {
                    TelaTesouro(
                        onClickRecomecar = {
                            navigationController.navigate("home") {
                                popUpTo("home") { inclusive = true }
                            }
                        }
                    )
                }
            }
        }
    }
}


@Composable
fun TelaHome(onClickIniciar: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Caça ao Tesouro", fontSize = 32.sp, modifier = Modifier.padding(bottom = 16.dp))
        Button(onClick = onClickIniciar) {
            Text("Iniciar a jornada")
        }
    }
}

@Composable
fun TelaPista(
    charada: String,
    onClickProxima: () -> Unit,
    onClickVoltar: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(charada, fontSize = 24.sp, modifier = Modifier.padding(16.dp))

        Button(onClick = onClickProxima, modifier = Modifier.padding(8.dp)) {
            Text("Próxima Pista")
        }

        Button(onClick = onClickVoltar) {
            Text("Voltar")
        }
    }
}

@Composable
fun TelaTesouro(onClickRecomecar: () -> Unit) {
    val context = LocalContext.current

    DisposableEffect(Unit) {
        val mediaPlayer = MediaPlayer.create(context, R.raw.hinovasco)
        mediaPlayer.start()

        onDispose {
            mediaPlayer.stop()
            mediaPlayer.release()
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Saudações Vascaínas!", fontSize = 24.sp, modifier = Modifier.padding(16.dp))
        Text("Você encontrou o tesouro!", fontSize = 20.sp, modifier = Modifier.padding(bottom = 16.dp))

        Image(
            painter = painterResource(id = R.drawable.vasco),
            contentDescription = "Imagem do Vasco",
            modifier = Modifier
                .size(250.dp)
                .padding(bottom = 24.dp)
        )

        Button(onClick = onClickRecomecar) {
            Text("Recomeçar")
        }
    }
}