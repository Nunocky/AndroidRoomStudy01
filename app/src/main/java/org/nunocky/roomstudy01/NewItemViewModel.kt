package org.nunocky.roomstudy01

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.nunocky.roomstudy01.database.TopicRepository
import org.nunocky.roomstudy01.database.room.Topic
import java.util.*

class NewItemViewModel(private val topicRepository: TopicRepository) : ViewModel() {
    class Factory(private val topicRepository: TopicRepository) :
        ViewModelProvider.NewInstanceFactory() {
        @Suppress("unchecked_cast")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return NewItemViewModel(topicRepository) as T
        }
    }

    enum class Status {
        INIT,
        DONE
    }

    val ready = MutableLiveData(false)
    var status = MutableLiveData(Status.INIT)
    val title = MutableLiveData("")

    // TODO fragmentに移動
    fun registerNewItem(title: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val entry = Topic(0, title, false, Date(), Date())
            topicRepository.insert(entry)
            status.postValue(Status.DONE)
        }
    }
}