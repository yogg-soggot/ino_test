package com.avein.ino.udf.store

import androidx.compose.runtime.Composable
import com.avein.ino.udf.FlowEffectHandler
import com.avein.ino.udf.ReactiveState
import com.avein.ino.udf.core.*
import com.avein.ino.udf.core.reducer.Reducer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import androidx.compose.runtime.State as ComposeState

class Store<Model, Event, Effect, Action> (
    private val _state: ReactiveState<Model>,
    private val reducer: Reducer<Model, Event, Effect, Action>,
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
                reducer(model, event).sideEffect(
                    { effect -> launch { effectHandler.dispatch(effect) } },
                    { action -> launch { actors.process(action) } },
                )
            }
        }
    }

    @Composable
    fun CollectEffects(collect: suspend (Effect) -> Unit) = effectHandler.CollectOnUi(collect)
}
