package com.avein.ino.udf.utils

inline fun <reified T: Any> T.isTypeOf(clazz: Class<T>) : Boolean {
    val klass = T::class.java
    return klass.isAssignableFrom(clazz)
}

inline fun <reified T1: Any, reified T2> typeOf(arg: T1) : Boolean {
    val klass = T2::class.java
    return klass.isAssignableFrom(arg.javaClass)
}
