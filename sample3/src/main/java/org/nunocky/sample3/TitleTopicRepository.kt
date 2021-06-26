package org.nunocky.sample3

import org.nunocky.sample3.database.AppDatabase
import org.nunocky.sample3.database.Text
import org.nunocky.sample3.database.Topic

class TitleTopicRepository(private val database: AppDatabase) {
    fun insertTopic(topic: Topic) {
        topic.id = database.getTopicDAO().insert(topic)
    }

    fun deleteTopic(topic: Topic) {
        database.getTopicDAO().delete(topic)
    }

    fun insertText(text: Text) {
        text.id = database.getTextDAO().insert(text)
    }

    fun findTopics() = database.getTopicDAO().findAll()

    fun allTexts() = database.getTextDAO().findAll()

    fun findTextsForTopic(topicId: Long) = database.getTextDAO().findAllRelated(topicId)

    fun findTopicWithTextsWithId(topicId: Long) =
        database.getTopicDAO().findTopicWithTextsWithId(topicId)
}