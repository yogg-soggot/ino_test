package com.avein.ino.udf.scope

import androidx.compose.runtime.compositionLocalOf
import java.lang.RuntimeException

val LocalEventDispatcher = compositionLocalOf { fun(arg: Any) { eventNotSent(arg) } }
val LocalActionDispatcher = compositionLocalOf { fun(arg: Any) { eventNotSent(arg) } }
val LocalEffectDispatcher = compositionLocalOf { fun(arg: Any) { eventNotSent(arg) } }
val LocalExternalEventDispatcher = compositionLocalOf { fun(arg: Any) { eventNotSent(arg) } }
val LocalNavigationDispatcher = compositionLocalOf { fun(arg: Any) { eventNotSent(arg) } }

internal fun eventNotSent(arg: Any) {
    throw EventNotSentException(arg)
}

internal class EventNotSentException(arg: Any) : RuntimeException("Event $arg was not sent!" +
        "Check if you wrapped your code in UdfScope")