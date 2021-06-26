package org.nunocky.sample3.database

import androidx.room.Embedded
import androidx.room.Relation

data class TopicAndTexts(
    @Embedded
    val topic: Topic,

    @Relation(
        parentColumn = "id",
        entityColumn = "topic_id"
    )
    val texts: List<Text>
)