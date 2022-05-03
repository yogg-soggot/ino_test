package com.avein.ino.udf.store

import com.avein.ino.udf.ActionRouter
import com.avein.ino.udf.FlowEffectHandler
import com.avein.ino.udf.ReactiveState
import com.avein.ino.udf.core.reducer.Reducer

fun <Model, Event, Effect, Action> buildStore(
    build: StoreBuilder<Model, Event, Effect, Action>.() -> Unit
): Store<Model, Event, Effect, Action> {
    val builder = StoreBuilder<Model, Event, Effect, Action>()
    builder.build()
    return Store(
        builder.state,
        builder.reducer,
        builder.effectHandler,
        builder.actors
    )
}

class StoreBuilder<Model, Event, Effect, Action> {
    lateinit var state: ReactiveState<Model>
    lateinit var reducer: Reducer<Model, Event, Effect, Action>
    val effectHandler = FlowEffectHandler<Effect>()
    val actors = ActionRouter<Action>()
}
