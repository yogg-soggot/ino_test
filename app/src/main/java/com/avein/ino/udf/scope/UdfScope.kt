package com.avein.ino.udf.scope

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.rememberCoroutineScope
import com.avein.ino.udf.Store
import com.avein.ino.udf.utils.safeCast

@Composable
fun <Model, Event, Action, Effect> UdfScope(
    store: Store<Model, Event, Action, Effect>,
    sendExternal: (Any) -> Unit = ::eventNotSent,
    content: @Composable () -> Unit
) {
    val coroutineScope = rememberCoroutineScope()
    val sendEvent = store.getSendEvent(coroutineScope).let(::safeCast)
    val sendAction = store.getSendAction(coroutineScope).let(::safeCast)
    val dispatchEffect = store.getDispatchEffect(coroutineScope).let(::safeCast)
    
    CompositionLocalProvider(
        LocalEventDispatcher provides sendEvent,
        LocalActionDispatcher provides sendAction,
        LocalEffectDispatcher provides dispatchEffect,
        LocalExternalEventDispatcher provides sendEvent,
        content = content
    )
}
