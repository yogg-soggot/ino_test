package com.avein.ino.udf.core.reducer

interface Reducer<Model, Event, Effect, Action> {
    operator fun invoke(state: Model, event: Event) : EffectRunner<Effect, Action, Model>
    fun update(fn: SideEffect<Effect, Action>.() -> Model): EffectRunner<Effect, Action, Model> {
        return EffectRunner(fn)
    }
}

fun interface EffectRunner<Effect, Action, Model> {
    fun runEffect(effect: SideEffect<Effect, Action>): Model
}

interface SideEffect<Effect, Action> {
    fun produce(effect: Effect)
    fun send(action: Action)
}
