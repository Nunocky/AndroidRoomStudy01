package org.nunocky.roomstudy01.database

import androidx.lifecycle.LiveData
import org.nunocky.roomstudy01.database.room.Topic
import org.nunocky.roomstudy01.database.room.TopicDAO

class TopicRepository(private val topicDao: TopicDAO) {
    // ソート、検索条件
    data class Filter(
        val orderBy: Int = 0,  // 0:createdAt, other:updatedAt
        val order: Int = 0, // 0:ASC, other:DESC
        val onlyFavorite: Boolean = false
    )

    fun insert(topic: Topic) = topicDao.insert(topic)
    fun update(topic: Topic) = topicDao.update(topic)
    fun delete(topic: Topic) = topicDao.delete(topic)
    fun getById(id: Int): LiveData<Topic> = topicDao.getById(id)
    fun getAll(): LiveData<List<Topic>> = topicDao.getAll()

    fun findAll(createdAt: Boolean, asc: Boolean, onlyFavorites: Boolean): LiveData<List<Topic>> =
        topicDao.findAll(createdAt, asc, onlyFavorites)
}