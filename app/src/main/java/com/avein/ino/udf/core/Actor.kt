package com.avein.ino.udf.core

interface Actor<Action> {
    val action: Class<Action>
    suspend fun process(action: Action)
}
