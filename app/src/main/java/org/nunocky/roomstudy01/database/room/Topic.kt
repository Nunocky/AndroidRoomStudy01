package org.nunocky.roomstudy01.database.room

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable
import java.util.*

@Entity(tableName = "topics")
data class Topic(
    @PrimaryKey(autoGenerate = true) val id: Int,
    var title: String,
    var fav: Boolean,
    val createdAt: Date,
    var updatedAt: Date
) : Serializable
