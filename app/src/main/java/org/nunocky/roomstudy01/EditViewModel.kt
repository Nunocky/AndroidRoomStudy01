package org.nunocky.roomstudy01

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class EditViewModel() : ViewModel() {
//class EditViewModel(private val topicRepository: TopicRepository) : ViewModel() {
//    class Factory(private val topicRepository: TopicRepository) :
//        ViewModelProvider.NewInstanceFactory() {
//        @Suppress("unchecked_cast")
//        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
//            return EditViewModel(topicRepository) as T
//        }
//    }

    val ready = MutableLiveData(false)
    val title = MutableLiveData("")

}