package com.avein.ino.udf.scope

import androidx.compose.runtime.Composable

@Composable
fun getDispatch(): Callbacks {
    return Callbacks(
        LocalEventDispatcher.current,
        LocalActionDispatcher.current,
        LocalEffectDispatcher.current,
        LocalExternalEventDispatcher.current,
        LocalNavigationDispatcher.current,
    )
}

class Callbacks(
    val event: (Any) -> Unit,
    val action: (Any) -> Unit,
    val effect: (Any) -> Unit,
    val external: (Any) -> Unit,
    val navEvent: (Any) -> Unit,
)
