package com.avein.ino.udf.core

interface Actors<A> {
    suspend fun process(action: A)
}
