package com.example.kotlinnoel

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kotlinnoel.ui.theme.KotlinNoelTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KotlinNoelTheme {
                CalculadoraApp()
            }
        }
    }
}

@Composable
fun CalculadoraApp() {

    var numero1 by remember { mutableStateOf("") }
    var numero2 by remember { mutableStateOf("") }
    var resultado by remember { mutableStateOf<String?>(null) }
    var mensajeError by remember { mutableStateOf<String?>(null) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = Color(0xFFF7F9FC)
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(24.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "Nintendora",
                    fontSize = 32.sp,
                    color = Color(0xFF4A90E2),
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                Text(
                    text = "Suma dos números fácilmente",
                    fontSize = 16.sp,
                    color = Color.Gray
                )
            }

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 12.dp)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    TextField(
                        value = numero1,
                        onValueChange = { input ->
                            if (input.all { it.isDigit() }) {
                                numero1 = input
                            }
                        },
                        label = { Text("Número 1") },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        modifier = Modifier.fillMaxWidth()
                    )

                    TextField(
                        value = numero2,
                        onValueChange = { input ->
                            if (input.all { it.isDigit() }) {
                                numero2 = input
                            }
                        },
                        label = { Text("Número 2") },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }

            Button(
                onClick = {
                    mensajeError = null
                    resultado = null
                    try {
                        val num1 = numero1.toDouble()
                        val num2 = numero2.toDouble()
                        resultado = "Resultado: ${num1 + num2}"
                    } catch (e: NumberFormatException) {
                        mensajeError = "Por favor, ingresa números válidos."
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4A90E2)),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp)
            ) {
                Text(text = "Calcular", color = Color.White, fontSize = 20.sp)
            }

            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                mensajeError?.let {
                    Text(
                        text = it,
                        color = MaterialTheme.colorScheme.error,
                        fontSize = 16.sp,
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                }

                resultado?.let {
                    Text(
                        text = it,
                        fontSize = 20.sp,
                        color = Color(0xFF4A90E2),
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CalculadoraAppPreview() {
    KotlinNoelTheme {
        CalculadoraApp()
    }
}