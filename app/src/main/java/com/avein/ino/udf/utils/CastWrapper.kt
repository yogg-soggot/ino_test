package com.avein.ino.udf.utils

import java.lang.ClassCastException
import java.lang.IllegalArgumentException

fun <T> safeCast(fn: (T) -> Unit): (Any) -> Unit {
    return { arg: Any ->
        try {
            fn(arg as T)
        } catch (e: ClassCastException) {
            throw IllegalArgumentException("This callback does not support this type of action" +
                    "Check if you wrapped your code in correct scope" +
                    "ClassCast message: ${e.message}")
        }
    }
}
