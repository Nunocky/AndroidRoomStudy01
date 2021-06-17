package org.nunocky.roomstudy01

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.nunocky.roomstudy01.database.TopicRepository
import org.nunocky.roomstudy01.database.room.Topic
import java.util.*

class NewItemViewModel : ViewModel() {
    enum class Status {
        INIT,
        DONE
    }

    val ready = MutableLiveData(false)
    var status = MutableLiveData(Status.INIT)

    fun registerNewItem(title: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val entry = Topic(0, title, false, Date(), Date())
            TopicRepository.instance().topicDao.insert(entry)
            status.postValue(Status.DONE)
        }
    }
}