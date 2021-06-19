package org.nunocky.roomstudy01.ui.main

import androidx.lifecycle.*
import org.nunocky.roomstudy01.database.TopicRepository
import org.nunocky.roomstudy01.database.room.Topic

class MainViewModel(private val topicRepository: TopicRepository) : ViewModel() {

    data class Filter(
        val orderBy: Int = 0,  // 0:createdAt, other:updatedAt
        val order: Int = 0, // 0:ASC, other:DESC
        val onlyFavorite: Boolean = false
    )

    class Factory(private val topicRepository: TopicRepository) :
        ViewModelProvider.NewInstanceFactory() {
        @Suppress("unchecked_cast")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return MainViewModel(topicRepository) as T
        }
    }

    val filter = MutableLiveData(Filter())

    val topicList: LiveData<List<Topic>> = Transformations.switchMap(filter) { filter ->
        val isAsc = filter.order == 0
        if (filter.orderBy == 0 && filter.onlyFavorite) {
            topicRepository.getAllFavoritesWithCreatedAt(isAsc)
        } else if (filter.orderBy == 0 && filter.onlyFavorite) {
            topicRepository.getAllWithCreatedAt(isAsc)
        } else if (filter.orderBy == 1 && filter.onlyFavorite) {
            topicRepository.getAllFavoritesWithUpdatedAt(isAsc)
        } else {
            topicRepository.getAllWithUpdatedAt(isAsc)
        }
    }

}