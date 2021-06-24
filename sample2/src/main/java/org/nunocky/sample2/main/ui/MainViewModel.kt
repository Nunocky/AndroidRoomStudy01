package org.nunocky.sample2.main.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import org.nunocky.sample2.MyApplication
import org.nunocky.sample2.TopicRepository
import org.nunocky.sample2.database.Topic

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = (application as MyApplication).repository

    val filter = MutableLiveData<TopicRepository.Filter>()

    val topics: LiveData<List<Topic>> = Transformations.switchMap(filter) { filter ->
        repository.findTopicsWithFilter(filter)
    }
}
