package org.nunocky.roomstudy01.database

import androidx.lifecycle.LiveData
import org.nunocky.roomstudy01.database.room.Topic
import org.nunocky.roomstudy01.database.room.TopicDAO

class TopicRepository(private val topicDao: TopicDAO) {
    fun insert(topic: Topic) = topicDao.insert(topic)
    fun update(topic: Topic) = topicDao.update(topic)
    fun delete(topic: Topic) = topicDao.delete(topic)
    fun getAll(): LiveData<List<Topic>> = topicDao.getAll()
    fun getById(id: Int): Topic? = topicDao.getById(id)

    fun findAll(
        forCreatedAt: Boolean,
        asc: Boolean
    ): LiveData<List<Topic>> = topicDao.findAll(forCreatedAt, asc)

    fun findAllFavorites(
        forCreatedAt: Boolean,
        asc: Boolean
    ): LiveData<List<Topic>> = topicDao.findAllFavorites(forCreatedAt, asc)

}