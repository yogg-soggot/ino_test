package com.avein.ino

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.avein.ino.udf.scope.LocalEventDispatcher
import com.avein.ino.ui.theme.InoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            InoTheme {
                val context = LocalContext.current
                val sendEvent = fun(text: String) {
                    Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
                } as (Any) -> Unit
                CompositionLocalProvider(LocalEventDispatcher provides sendEvent) {
                    val send = LocalEventDispatcher.current
                    Text(text = "Click", Modifier.clickable { send("Event") })
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
    InoTheme {
        Greeting("Android")
    }
}
