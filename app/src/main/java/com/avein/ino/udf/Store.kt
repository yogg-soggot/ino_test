package com.avein.ino.udf

import androidx.compose.runtime.Composable
import com.avein.ino.udf.core.*
import com.avein.ino.udf.core.reducer.Reducer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import androidx.compose.runtime.State as ComposeState

class Store<Model, Event, Action, Effect> (
    private val _state: ReactiveState<Model>,
    private val reducer: Reducer<Model, Event, Action>,
    private val effectHandler: FlowEffectHandler<Effect>,
    private val actors: Actors<Action>,
) {
    val state: ComposeState<Model>
        @Composable
        get() = _state.state

    @Composable
    fun getSendEvent(scope: CoroutineScope) = fun(event: Event) {
        scope.launch {
            _state.update { model ->
                reducer(model, event).runEffect(actors::process)
            }
        }
    }

    @Composable
    fun CollectEffects(collect: suspend (Effect) -> Unit) = effectHandler.CollectOnUi(collect)
}
