package me.gumify.hiperexample

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import me.gumify.hiper.http.Hiper
import me.gumify.hiper.http.data.HiperResponse
import me.gumify.hiper.util.debug
import me.gumify.hiperexample.ui.theme.HiperExampleTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val http = Hiper.getInstance().async()
        http.get("https://httpbin.org/ip") { response ->
            Log.d("hello", response.toString())
        }
        http.head("https://httpbin.org/ip") { response: HiperResponse ->
        }
        http.post("https://httpbin.org/post") { response ->
            debug()
        }

        setContent {
            HiperExampleTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Greeting("Android")
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