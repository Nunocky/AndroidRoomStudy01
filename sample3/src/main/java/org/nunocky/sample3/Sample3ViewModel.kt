package org.nunocky.sample3

import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.nunocky.sample3.database.Text
import org.nunocky.sample3.database.Topic

class Sample3ViewModel(private val repository: TitleTopicRepository) : ViewModel() {

    class Factory(private val repository: TitleTopicRepository) :
        ViewModelProvider.NewInstanceFactory() {
        @Suppress("unchecked_cast")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return Sample3ViewModel(repository) as T
        }
    }

    // Topic一覧に表示するデータ
    var topics = repository.findTopics()

    // Text一覧に表示するデータ
    var texts = repository.allTexts()

    // 選択中の Topic
    var topic = MutableLiveData<Topic>()

    // 選択中の Topicに関連した Textのリスト
    var relatedTexts = Transformations.switchMap(topic) {
        repository.findTextsForTopic(it.id)
    }

    fun addNewItem(topic: Topic, text1: Text, text2: Text) = viewModelScope.launch(Dispatchers.IO) {
        repository.insertTopic(topic)

        text1.topic_id = topic.id
        text2.topic_id = topic.id
        repository.insertText(text1)
        repository.insertText(text2)
    }

    fun deleteItem(topic: Topic) = viewModelScope.launch(Dispatchers.IO) {
        repository.deleteTopic(topic)
    }
}