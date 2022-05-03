package com.avein.ino.udf

import com.avein.ino.udf.core.Actor
import com.avein.ino.udf.core.Actors

class ActionRouter<Action> : Actors<Action> {
    private var actors = hashMapOf<Class<Action>, Actor<Action>>()
    override suspend fun process(action: Action) {
        actors[action!!::class.java]?.process(action)
    }
    operator fun plusAssign(actor: Actor<Action>) {
        actors[actor.action] = actor
    }
}
