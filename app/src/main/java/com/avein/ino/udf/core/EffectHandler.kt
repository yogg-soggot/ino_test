package com.avein.ino.udf.core

interface EffectHandler<Effect> {
    suspend fun dispatch(effect: Effect)
}
