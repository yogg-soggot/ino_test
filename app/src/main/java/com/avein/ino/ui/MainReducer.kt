package com.avein.ino.ui

import com.avein.ino.udf.core.reducer.Reducer
import com.avein.ino.udf.core.reducer.Update

class MainReducer : Reducer<MyModel, MyEvent, MyEffect, MyAction> {
    override fun invoke(
        state: MyModel, event: MyEvent
    ): Update<MyEffect, MyAction, MyModel> {
        return when(event) {
            MyEvent.Decrement -> update {
                state.copy(number = state.number - 1)
            }
            MyEvent.Increment -> update {
                if (state.number == 9) produce(MyEffect.Toast("Yeet"))
                state.copy(number = state.number + 1)
            }
            MyEvent.RunTimer -> update {
                if(state.timerRunning) {
                    send(MyAction.StartTimer)
                } else send(MyAction.StopTimer)
                state.copy(timerRunning = !state.timerRunning)
            }
            is MyAction -> update {
                send(event)
                state
            }
            is MyEffect -> update {
                produce(event)
                state
            }
        }
    }
}



sealed interface MyEvent {
    object Increment : MyEvent
    object Decrement : MyEvent
    object RunTimer : MyEvent
}

sealed class MyEffect {
    class Toast(val text: String): MyEffect(), MyEvent
}

sealed interface MyAction {
    object StartTimer: MyAction
    object StopTimer: MyAction
}

data class MyModel(
    val number: Int,
    val timerRunning: Boolean = false,
)
