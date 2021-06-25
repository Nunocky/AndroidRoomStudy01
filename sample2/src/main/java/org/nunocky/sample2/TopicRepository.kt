package org.nunocky.sample2

import androidx.lifecycle.LiveData
import org.nunocky.sample2.database.AppDatabase
import org.nunocky.sample2.database.Topic

class TopicRepository(private val database: AppDatabase) {
    data class Filter(
        var tag1: Boolean,
        var tag2: Boolean,
        var tag3: Boolean,
        var fav: Boolean = false,
    )

    fun findTopicsWithFilter(filter: Filter): LiveData<List<Topic>> {
        val tagIds = ArrayList<Int>().apply {
            if (filter.tag1) {
                add(1)
            }
            if (filter.tag2) {
                add(2)
            }
            if (filter.tag3) {
                add(3)
            }
        }

        val dao = database.getTopicDAO()
        return dao.findByTagIds(tag_ids = tagIds.toIntArray(), onlyFavorites = filter.fav)
    }
}