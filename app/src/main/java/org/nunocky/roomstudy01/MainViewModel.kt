package org.nunocky.roomstudy01

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.nunocky.roomstudy01.database.TopicRepository
import org.nunocky.roomstudy01.database.room.Topic
import org.nunocky.roomstudy01.database.room.TopicDAO

class MainViewModel : ViewModel() {
    var topicList: LiveData<List<Topic>>

    private var dao: TopicDAO = TopicRepository.instance().topicDao

    init {
        topicList = dao.getAll()
    }

    fun toggleFav(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val dao = TopicRepository.instance().topicDao
            val topic = dao.getById(id)
            topic?.let {
                topic.fav = !topic.fav
                dao.update(topic)
            }
        }
    }
}