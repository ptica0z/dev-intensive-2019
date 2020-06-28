package ru.skillbranch.devintensive.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import ru.skillbranch.devintensive.extensions.mutableLiveData
import ru.skillbranch.devintensive.models.data.ChatItem
import ru.skillbranch.devintensive.models.data.UserItem
import ru.skillbranch.devintensive.repositories.ChatRepository
import ru.skillbranch.devintensive.utils.DataGenerator

class MainViewModel : ViewModel() {

    private val query = mutableLiveData("")
    private val chatRepository = ChatRepository
    private val chats = Transformations.map(chatRepository.loadChats()){chats ->
        return@map chats.filter { !it.isArchived }
            .map { it.toChatItem()}
            .sortedBy { it.id.toInt() }
    }
    private val archive = Transformations.map(chatRepository.loadChats()){chats ->
        return@map chats.filter { it.isArchived }
            .map { it.toChatItem()}
            .sortedBy { it.id.toInt() }
    }

    fun getChatData() : LiveData<List<ChatItem>>{
        //return chats

        val result = MediatorLiveData<List<ChatItem>>()

        val filterF = {
            val queryStr = query.value!!
            val chatList = chats.value!!

            result.value = if(queryStr.isEmpty()) chatList else chatList.filter {
                it.title.contains(queryStr, true)
            }
        }

        result.addSource(chats){filterF.invoke()}
        result.addSource(query){filterF.invoke()}
        return result
    }

    fun addToArchive(chatId: String) {
        val chat = chatRepository.finde(chatId)
        chat ?: return
        chatRepository.update(chat.copy(isArchived = true))
    }

    fun restoreFromArchive(chatId: String){
        val chat = chatRepository.finde(chatId)
        chat ?: return
        chatRepository.update(chat.copy(isArchived = false))
    }

    fun handleSearchQuery(text: String) {
        query.value = text
    }
}