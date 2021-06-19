package org.nunocky.roomstudy01.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.nunocky.roomstudy01.database.TopicRepository
import org.nunocky.roomstudy01.database.room.Topic

class MainViewModel(private val topicRepository: TopicRepository) : ViewModel() {

    class Factory(private val topicRepository: TopicRepository) :
        ViewModelProvider.NewInstanceFactory() {
        @Suppress("unchecked_cast")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return MainViewModel(topicRepository) as T
        }
    }

    var topicList: LiveData<List<Topic>> = topicRepository.getAll()

    fun toggleFav(topic: Topic) {
        viewModelScope.launch(Dispatchers.IO) {
            topic.fav = !topic.fav
            topicRepository.update(topic)
        }
    }

    fun deleteTopic(topic: Topic) {
        viewModelScope.launch(Dispatchers.IO) {
            topicRepository.delete(topic)
        }
    }
}