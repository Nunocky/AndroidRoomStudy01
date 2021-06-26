package org.nunocky.sample3.database

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "texts",
    foreignKeys = [ForeignKey(
        entity = Topic::class,
        parentColumns = arrayOf("id"),      // Topicの idを
        childColumns = arrayOf("topic_id"), // Textの topic_idと関連付ける
        onDelete = ForeignKey.CASCADE       // Topicを削除したとき、関連付けられた Textも削除する
    )]
)
data class Text(
    @PrimaryKey(autoGenerate = true) var id: Long,
    var text: String,
    var topic_id: Long = 0
)
