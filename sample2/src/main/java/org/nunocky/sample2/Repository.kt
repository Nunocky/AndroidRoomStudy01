package org.nunocky.sample2

import androidx.lifecycle.LiveData
import org.nunocky.sample2.database.AppDatabase
import org.nunocky.sample2.database.Topic

class Repository(private val database: AppDatabase) {
    data class Filter(
        var tag1: Boolean,
        var tag2: Boolean,
        var tag3: Boolean,
    )

    fun findTopicsWithFilter(filter: Filter): LiveData<List<Topic>> {
        val tagIds = ArrayList<Int>()
        if (filter.tag1) {
            tagIds.add(1)
        }
        if (filter.tag2) {
            tagIds.add(2)
        }
        if (filter.tag3) {
            tagIds.add(3)
        }

        val dao = database.getTopicDAO()
        return dao.findByTagIds(tagIds.toIntArray())
    }
}