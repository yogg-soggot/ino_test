package com.avein.ino.udf.core

interface State<Model> {
    suspend fun update(reduce: (state: Model) -> Model)
}
