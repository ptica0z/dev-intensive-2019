package ru.skillbranch.dev_intensive.models

import java.util.*

abstract class BaseMessage(
    val id: String,
    val from: User?,
    val chat: Chat,
    val isIncoming: Boolean = false,
    val date: Date = Date()
) {
    abstract fun formatMessage() : String
    companion object AbstractFactoryfy{
        var lastId = -1
        fun makeMessage(from: User?, chat: Chat, date: Date = Date(), type: String = "text", payload: Any?): BaseMessage{
            lastId++
            return when(type){
                "text"-> TextMessage("$lastId", from, chat, date=date, text  = payload as String)
                else ->ImageMessage("$lastId", from, chat, date=date, image= payload as String)
            }
        }
    }
}