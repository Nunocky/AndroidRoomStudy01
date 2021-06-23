package org.nunocky.sample2

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import org.nunocky.sample2.database.Topic

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = (application as MyApplication).repository

    val filter = MutableLiveData<Repository.Filter>()

    val topics: LiveData<List<Topic>> = Transformations.switchMap(filter) { filter ->
        repository.findTopicsWithFilter(filter)
    }

    private var count = 0

    fun update() {
        when (count % 3) {
            1 -> {
                filter.value = Repository.Filter(true, false, false)
            }
            2 -> {
                filter.value = Repository.Filter(false, true, false)
            }
            else -> {
                filter.value = Repository.Filter(false, false, true)
            }
        }
        count += 1
    }
}
