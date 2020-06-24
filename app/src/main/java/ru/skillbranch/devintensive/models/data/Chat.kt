package ru.skillbranch.devintensive.models.data

import ru.skillbranch.devintensive.extensions.shortFormat
import ru.skillbranch.devintensive.models.BaseMessage
import ru.skillbranch.devintensive.models.TextMessage
import ru.skillbranch.devintensive.utils.Utils
import java.util.*

data class Chat(
    val id: String,
    val title: String,
    val members: List<User> = listOf(),
    var messages: MutableList<BaseMessage> = mutableListOf(),
    var isArchived : Boolean = false
) {
    fun unreadableMessageCount() : Int{
        val n = messages.filter { it.isReaded == false }.size
        return n
        //return 0
    }

    fun lastMessageDate() : Date? {
        //TODO implement me
        var lastDate : Date?
        lastDate = if(messages.isEmpty()) null else messages[0].date
        messages.map {
            if(it.date > lastDate){
                lastDate = it.date
            }
        }
        return lastDate
    }

    fun lastMessageShort() : Pair<String?, String>{
        val lastDate = lastMessageDate()
        if(lastDate == null) return "Сообщений ещё нет" to "@John_Doe"
        val lastMessage = messages.find { it.date == lastDate }
        if(lastMessage is TextMessage) return lastMessage.text to "${lastMessage.from.firstName}"
        else return "${lastMessage!!.from.firstName} - отправил фото" to "${lastMessage!!.from.firstName}"
    }

    private fun isSingle() : Boolean = members.size == 1
    fun toChatItem(): ChatItem {
        return if (isSingle()){
            val user = members.first()
            ChatItem(
                id,
                user.avatar,
                Utils.toInitials(user.firstName, user.lastName) ?: "??",
                "${user.firstName ?: ""} ${user.lastName ?: ""}",
                lastMessageShort().first,
                unreadableMessageCount(),
                lastMessageDate()?.shortFormat(),
                user.isOnline
            )
        }else{
            ChatItem(
                id,
                null,
                "",
                title,
                lastMessageShort().first,
                unreadableMessageCount(),
                lastMessageDate()?.shortFormat(),
                false,
                ChatType.GROUP,
                lastMessageShort().second
            )
        }
    }
}

enum class ChatType{
    SINGLE,
    GROUP,
    ARCHIVE
}
