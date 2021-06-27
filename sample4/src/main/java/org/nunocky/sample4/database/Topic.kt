package org.nunocky.sample4.database

import android.net.Uri
import androidx.room.*

@Entity
data class Topic(
    @PrimaryKey(autoGenerate = true)
    val id: Long,

    val title: String,
    val imageUri: Uri,
    val thumbnailUri: Uri,
)


