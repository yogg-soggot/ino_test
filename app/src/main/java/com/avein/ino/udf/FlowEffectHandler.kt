package com.avein.ino.udf

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.avein.ino.udf.core.EffectHandler
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class FlowEffectHandler<Effect>: EffectHandler<Effect> {
    private val flow = MutableSharedFlow<Effect>()
    override suspend fun dispatch(effect: Effect) {
        flow.emit(effect)
    }

    @Composable
    fun CollectOnUi(collect: suspend (Effect) -> Unit) {
        LaunchedEffect(Unit) {
            flow.collect { launch { collect(it) } }
        }
    }
}
