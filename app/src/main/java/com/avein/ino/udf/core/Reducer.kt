package com.avein.ino.udf.core

import com.avein.ino.udf.core.reducer.EffectRunner
import com.avein.ino.udf.core.reducer.Reducer

class MyReducer : Reducer<MyModel, MyEvent, MyEffect, MyAction> {
    override fun invoke(state: MyModel, event: MyEvent): EffectRunner<MyEffect, MyAction, MyModel> {
        return when(event) {
            is MyEvent.Rename -> update {
                produce(MyEffect.Save(state.name))
                send(MyAction())
                state
            }
            MyEvent.Save -> update {
                state
            }
        }
    }
}

data class MyModel(val name: String)

sealed class MyEvent {
    data class Rename(val newName: String) : MyEvent()
    object Save : MyEvent()
}

sealed class MyEffect {
    data class Save(val name: String) : MyEffect()
    object Load : MyEffect()
}

class MyAction