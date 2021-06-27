package org.nunocky.sample4.database

import android.net.Uri
import androidx.room.TypeConverter

class DataConverter {
    @TypeConverter
    fun stringToUri(str: String): Uri {
        return Uri.parse(str)
    }

    @TypeConverter
    fun uriToString(uri: Uri): String {
        return uri.toString()
    }
}