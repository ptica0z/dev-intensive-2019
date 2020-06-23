package ru.skillbranch.devintensive.models.data

import androidx.appcompat.widget.DialogTitle
import ru.skillbranch.devintensive.extensions.shortFormat
import ru.skillbranch.devintensive.models.BaseMessage
import ru.skillbranch.devintensive.utils.Utils
import java.util.*

data class Chat(
    val id: String,
    val title: String,
    val members: List<User> = listOf(),
    var messages: MutableList<BaseMessage> = mutableListOf(),
    var isArchived : Boolean = false
) {
    fun unreadableMessageCounter() : Int{
        // TODO implement me
        return 0
    }

    private fun lastMessageDate() : Date {
        //TODO implement me
        return Date()
    }

    private fun lastMessageShort() : Pair<String, String>{
        // TODO implement me
        return "Сообщений ещё нет" to "@John_Doe"
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
                unreadableMessageCounter(),
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
                unreadableMessageCounter(),
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
