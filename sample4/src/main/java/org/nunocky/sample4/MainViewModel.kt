package org.nunocky.sample4

import android.net.Uri
import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.nunocky.sample4.database.Topic
import org.nunocky.sample4.database.TopicRepository

class MainViewModel(app: MyApplication, private val repository: TopicRepository) :
    AndroidViewModel(app) {

    class Factory(private val app: MyApplication, private val repository: TopicRepository) :
        ViewModelProvider.NewInstanceFactory() {
        @Suppress("unchecked_cast")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return MainViewModel(app, repository) as T
        }
    }

    val topics = repository.findAll()

    val current = MutableLiveData<Topic>()

    fun initDatabase() {
        viewModelScope.launch(Dispatchers.IO) {
            //repository.deleteAll()
            if (repository.count() == 0) {
                arrayOf("red", "green", "blue").forEach {
                    val uri = Uri.parse("file:///android_asset/${it}.png")
                    val thumbUri = Uri.parse("file:///android_asset/${it}_thumb.png")
                    val topic = Topic(0, it, uri, thumbUri)
                    repository.insert(topic)
                }
            }
        }
    }
}