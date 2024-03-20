package com.example.intent

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
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

                        //Intent with  arguments
                        //Need to add queries block in manifest
                        ExplicitIntentWithArgs(context)
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
        val intent = Intent(Intent.ACTION_MAIN).also {
            it.`package` = "com.google.android.youtube"
        }
        try {
            context.startActivity(intent)
        }catch (e: ActivityNotFoundException){
            Toast.makeText(context,e.localizedMessage, Toast.LENGTH_SHORT).show()
        }
    }) {
        Text(text = "Explicit Intent")
    }
}

@Composable
fun ExplicitIntentWithArgs(context: Context) {
    Button(onClick = {
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "text/plain"
        intent.putExtra(Intent.EXTRA_EMAIL, arrayOf("rasel.cse19.ruet@gmail.com"))
        intent.putExtra(Intent.EXTRA_SUBJECT, "Sample mail subject")
        intent.putExtra(Intent.EXTRA_TEXT, "This is sample mail body")
        try {
            context.startActivity(intent)
        }catch (e: Exception){
            Toast.makeText(context,e.localizedMessage, Toast.LENGTH_SHORT).show()
        }
    }) {
        Text(text = "Explicit Intent With Args")
    }
}

