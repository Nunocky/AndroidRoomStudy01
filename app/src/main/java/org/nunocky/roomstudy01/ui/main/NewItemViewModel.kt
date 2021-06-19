package org.nunocky.roomstudy01.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class NewItemViewModel() : ViewModel() {
//class NewItemViewModel(private val topicRepository: TopicRepository) : ViewModel() {
//    class Factory(private val topicRepository: TopicRepository) :
//        ViewModelProvider.NewInstanceFactory() {
//        @Suppress("unchecked_cast")
//        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
//            return NewItemViewModel(topicRepository) as T
//        }
//    }

    val ready = MutableLiveData(false)
    val title = MutableLiveData("")
}