package org.nunocky.roomstudy01.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
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
}