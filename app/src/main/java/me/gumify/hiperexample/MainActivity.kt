package me.gumify.hiperexample

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import me.gumify.hiper.http.Hiper
import me.gumify.hiper.http.data.HiperResponse
import me.gumify.hiper.util.*
import me.gumify.hiperexample.ui.theme.HiperExampleTheme
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        

        setContent {
            HiperExampleTheme {
                val context = LocalContext.current
                val tart = remember { Tart(context = context, name = "test_store") }
                val textState = tart.getString("name").collectAsState(initial = "hello, world")

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Box {
                        Column {
                            Text(text = textState.value, style = MaterialTheme.typography.h5)
                            Button(onClick = {
                                async {
                                    tart.put("name", "jeeva : ${(0..10).random()}")
                                }
                            }) {
                                Text(text = "Click me!")
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    HiperExampleTheme {
        Greeting("Android")
    }
}