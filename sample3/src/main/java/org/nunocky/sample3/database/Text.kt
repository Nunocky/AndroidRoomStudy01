package org.nunocky.sample3.database

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "texts",
    foreignKeys = [ForeignKey(
        entity = Topic::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("topic_id"),
        onDelete = ForeignKey.CASCADE
    )]
)
data class Text(
    @PrimaryKey(autoGenerate = true) var id: Long,
    var text: String,
    var topic_id: Long = 0
)
