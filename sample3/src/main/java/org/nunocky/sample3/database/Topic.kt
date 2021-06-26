package org.nunocky.sample3.database

import androidx.room.*

@Entity(tableName = "topics")
data class Topic(
    @PrimaryKey(autoGenerate = true) var id: Long,
    var title: String
)
