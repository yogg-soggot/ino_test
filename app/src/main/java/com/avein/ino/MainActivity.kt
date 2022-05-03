package com.avein.ino

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.avein.ino.udf.ReactiveState
import com.avein.ino.udf.store.buildStore
import com.avein.ino.ui.*
import com.avein.ino.ui.theme.InoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            InoTheme {
                val state = store.state
                val send = store.getSendEvent(rememberCoroutineScope())
                val context = LocalContext.current
                store.CollectEffects {
                    when(it) {
                        is MyEffect.Toast -> Toast.makeText(context, it.text, Toast.LENGTH_SHORT).show()
                    }
                }
                Column(
                    Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.SpaceEvenly,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Icon(
                        Icons.Filled.KeyboardArrowUp, contentDescription = null,
                        Modifier.clickable { send(MyEvent.Increment) }
                    )
                    Text(text = "number ${state.value}", Modifier.clickable { send(MyEvent.RunTimer) })
                    Icon(
                        Icons.Filled.KeyboardArrowDown, contentDescription = null,
                        Modifier.clickable { send(MyEvent.Decrement) }
                    )
                }
            }
        }
    }
}

val store = buildStore<MyModel, MyEvent, MyEffect, MyAction> {
    state = ReactiveState(MyModel(0))
    reducer = MainReducer()
}