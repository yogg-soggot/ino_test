package com.avein.ino.udf.core.reducer

interface Reducer<Model, Event, Effect> {
    operator fun invoke(state: Model, event: Event) : EffectRunner<Effect, Model>
    fun update(fn: SideEffect<Effect>.() -> Model): EffectRunner<Effect, Model> {
        return EffectRunner(fn)
    }
}

fun interface EffectRunner<Effect, Model> {
    fun runEffect(effect: SideEffect<Effect>): Model
}

fun interface SideEffect<Effect> {
    fun produce(effect: Effect)
}
