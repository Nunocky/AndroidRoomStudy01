package org.nunocky.roomstudy01.ui.main

import androidx.lifecycle.*
import org.nunocky.roomstudy01.database.TopicRepository
import org.nunocky.roomstudy01.database.room.Topic

class TopicListViewModel(private val topicRepository: TopicRepository) : ViewModel() {

    data class Filter(
        val orderBy: Int = 0,  // 0:createdAt, other:updatedAt
        val order: Int = 0, // 0:ASC, other:DESC
        val onlyFavorite: Boolean = false
    )

    class Factory(private val topicRepository: TopicRepository) :
        ViewModelProvider.NewInstanceFactory() {
        @Suppress("unchecked_cast")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return TopicListViewModel(topicRepository) as T
        }
    }

    val filter = MutableLiveData(Filter())

    val topicList: LiveData<List<Topic>> = Transformations.switchMap(filter) { filter ->
        val createdAt = filter.orderBy == 0
        val asc = filter.order == 0
        val onlyFavorite = filter.onlyFavorite

        if (onlyFavorite)
            topicRepository.findAllFavorites(createdAt, asc)
        else
            topicRepository.findAll(createdAt, asc)
    }

}