package org.nunocky.roomstudy01

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.nunocky.roomstudy01.database.TopicRepository
import org.nunocky.roomstudy01.database.room.Topic

class EditViewModel(private val topicRepository: TopicRepository) : ViewModel() {
    class Factory(private val topicRepository: TopicRepository) :
        ViewModelProvider.NewInstanceFactory() {
        @Suppress("unchecked_cast")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return EditViewModel(topicRepository) as T
        }
    }

    enum class Status {
        Init,
        Done
    }

    val ready = MutableLiveData(false)
    val title = MutableLiveData("")
    val status = MutableLiveData(Status.Init)

    fun updateTopic(topic: Topic) {
        viewModelScope.launch(Dispatchers.IO) {
            topicRepository.update(topic)
            status.postValue(Status.Done)
        }
    }
}