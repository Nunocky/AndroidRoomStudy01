package org.nunocky.sample2.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Topic(
    @PrimaryKey val id: Int,
    val title: String
)