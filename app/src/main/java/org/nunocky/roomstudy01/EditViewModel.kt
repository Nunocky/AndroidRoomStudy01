package org.nunocky.roomstudy01

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.nunocky.roomstudy01.database.TopicRepository
import org.nunocky.roomstudy01.database.room.Topic

class EditViewModel : ViewModel() {
    enum class Status {
        Init,
        Done
    }

    val ready = MutableLiveData(false)
    val title = MutableLiveData("")
    val status = MutableLiveData(Status.Init)

    fun updateTopic(topic: Topic) {
        viewModelScope.launch(Dispatchers.IO) {
            val dao = TopicRepository.instance().topicDao
            dao.update(topic)
            status.postValue(Status.Done)
        }

    }
}