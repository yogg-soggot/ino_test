package com.avein.ino.udf

import androidx.compose.runtime.Composable
import com.avein.ino.udf.core.*
import com.avein.ino.udf.core.reducer.Reducer
import com.avein.ino.udf.core.reducer.SideEffect
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import androidx.compose.runtime.State as ComposeState

class Store<Model, Event, Action, Effect> (
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
                reducer(model, event).runEffect(Fx(scope))
            }
        }
    }

    inner class Fx(private val scope: CoroutineScope): SideEffect<Effect, Action> {
        override fun produce(effect: Effect) {
            scope.launch { effectHandler.dispatch(effect) }
        }

        override fun send(action: Action) {
            actors.process(action)
        }
    }

    @Composable
    fun CollectEffects(collect: suspend (Effect) -> Unit) = effectHandler.CollectOnUi(collect)
}
