package com.avein.ino.udf

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.avein.ino.udf.core.State
import kotlinx.coroutines.flow.MutableStateFlow
import androidx.compose.runtime.State as ComposeState

class ReactiveState<Model>(
    initial: Model,
): State<Model> {
    private val stateFlow = MutableStateFlow(initial)

    val state: ComposeState<Model>
        @Composable
        get() = stateFlow.collectAsState()

    override suspend fun update(reduce: (state: Model) -> Model) {
        stateFlow.emit(reduce(stateFlow.value))
    }
}
