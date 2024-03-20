package com.example.intent

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.ContextCompat.startActivity
import com.example.intent.ui.theme.IntentTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val context = this
        setContent {
            IntentTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize() ,
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        ImplicitIntentButton(context)

                        //Directly open an app
                        ExlicitIntentButton(context)
                    }
                }
            }
        }
    }
}

@Composable
fun ImplicitIntentButton(context: Context) {
    Button(onClick = {
        Intent(context, MainActivity2::class.java).also {
            context.startActivity(it)
        }
    }) {
        Text(text = "Implicit Intent")
    }
}
@Composable
fun ExlicitIntentButton(context: Context) {
    Button(onClick = {
        Intent(Intent.ACTION_MAIN).also {
            it.`package` = "com.google.android.youtube"
            context.startActivity(it)
        }
    }) {
        Text(text = "Implicit Intent")
    }
}

