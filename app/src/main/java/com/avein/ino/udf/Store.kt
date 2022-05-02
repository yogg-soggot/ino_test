package com.avein.ino.udf

import androidx.compose.runtime.Composable
import com.avein.ino.udf.core.*
import com.avein.ino.udf.utils.isTypeOf
import com.avein.ino.udf.utils.typeOf
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import androidx.compose.runtime.State as ComposeState

class Store<Model, Event, Action, Effect> (
    private val _state: ReactiveState<Model>,
    private val reducer: Reducer<Model, Event>,
    private val effectHandler: FlowEffectHandler<Effect>,
    private val actors: Actors<Action>,
) {
    val state: ComposeState<Model>
        @Composable
        get() = _state.state

    @Composable
    fun getSendEvent(scope: CoroutineScope) = fun(event: Event) {
        scope.launch {
            _state.update { reducer(it, event) }
        }
    }

    @Composable
    fun getSendAction(scope: CoroutineScope) = fun(action: Action) {
        scope.launch { actors.process(action) }
    }

    @Composable
    fun getDispatchEffect(scope: CoroutineScope) = fun(effect: Effect) {
        scope.launch { effectHandler.dispatch(effect) }
    }

    @Composable
    fun CollectEffects(collect: suspend (Effect) -> Unit) = effectHandler.CollectOnUi(collect)
}
