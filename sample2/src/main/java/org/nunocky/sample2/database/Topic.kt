package org.nunocky.sample2.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Topic(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val title: String = "",
    val fav: Boolean = false
)