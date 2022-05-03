package com.avein.ino.ui

import com.avein.ino.udf.core.Actor

class TimerActor: Actor<MyAction> {
    override val action = MyAction::class.java
    override suspend fun process(action: MyAction) {
        TODO("Отправляет событие Tick каждую ceкунду если приходит Action.Start " +
                "и останавливается если приходит Stop")
    }
}
