package com.avein.ino.udf.core.reducer

interface Reducer<Model, Event, Effect, Action> {
    operator fun invoke(state: Model, event: Event) : Update<Effect, Action, Model>
    fun update(
        update: Command<Effect, Action>.() -> Model
    ): Update<Effect, Action, Model> {
        return Update { effect, action ->
            update(Command(effect, action))
        }
    }
}

fun interface Update<Effect, Action, Model> {
    fun sideEffect(
        effect: (Effect) -> Unit,
        action: (Action) -> Unit
    ): Model
}

class Command<Effect, Action> (
    val produce: (Effect) -> Unit,
    val send: (Action) -> Unit,
)

fun <Effect, Action, Model> Update<Effect, Action, Model>.pure() = sideEffect({}, {})