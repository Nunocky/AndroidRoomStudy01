package org.nunocky.sample2.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Tag(
    @PrimaryKey val id: Long = 0,
    val title: String = ""
)