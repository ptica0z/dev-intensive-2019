package ru.skillbranch.devintensive.models.data

import java.io.FileDescriptor

data class ChatItem (
    val id : String,
    val avatar : String?,
    val initials : String,
    val title : String,
    val shortDescription: String?,
    val messageCount : Int,
    val lastMessageDate : String?,
    val isOnline : Boolean = false
)