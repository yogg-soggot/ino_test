package com.avein.ino.udf.core

typealias Reducer<Model, Event> = (state: Model, action: Event) -> Model
