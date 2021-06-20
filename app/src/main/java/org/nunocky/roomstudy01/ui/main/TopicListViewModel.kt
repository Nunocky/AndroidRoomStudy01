package org.nunocky.roomstudy01.ui.main

import androidx.lifecycle.*
import org.nunocky.roomstudy01.database.TopicRepository
import org.nunocky.roomstudy01.database.room.Topic

class TopicListViewModel(private val topicRepository: TopicRepository) : ViewModel() {

    class Factory(private val topicRepository: TopicRepository) :
        ViewModelProvider.NewInstanceFactory() {
        @Suppress("unchecked_cast")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return TopicListViewModel(topicRepository) as T
        }
    }

    // ソート、検索条件
    val filter = MutableLiveData(TopicRepository.Filter())

    // ListViewに表示するデータ
    val topicList: LiveData<List<Topic>> = Transformations.switchMap(filter) { filter ->
        topicRepository.findAll(
            createdAt = filter.orderBy == 0,
            asc = filter.order == 0,
            onlyFavorites = filter.onlyFavorite
        )
    }
}