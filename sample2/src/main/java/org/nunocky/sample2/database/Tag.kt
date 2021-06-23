package org.nunocky.sample2.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Tag(
    @PrimaryKey val id: Int,
    val titile: String
)