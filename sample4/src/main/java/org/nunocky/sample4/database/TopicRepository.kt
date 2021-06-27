package org.nunocky.sample4.database

class TopicRepository(private val database: TopicDatabase) {
    private val dao = database.getTopicDAO()

    fun insert(topic: Topic) = dao.insert(topic)
    fun findAll() = dao.findAll()
    fun count() = dao.count()
    fun deleteAll() = dao.deleteAll()
}