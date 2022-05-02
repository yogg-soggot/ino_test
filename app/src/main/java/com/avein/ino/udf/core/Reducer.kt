package com.avein.ino.udf.core

import com.avein.ino.udf.core.reducer.Update
import com.avein.ino.udf.core.reducer.Reducer

class MyReducer : Reducer<MyModel, MyEvent, MyEffect, MyAction> {
    override fun invoke(state: MyModel, event: MyEvent): Update<MyEffect, MyAction, MyModel> {
        return when(event) {
            is MyEvent.Rename -> update {
                produce(MyEffect.Save(state.name))
                state
            }
            MyEvent.Save -> update {
                send(MyAction.Save(state))
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

sealed class MyAction {
    class Save(val state: MyModel): MyAction()
}