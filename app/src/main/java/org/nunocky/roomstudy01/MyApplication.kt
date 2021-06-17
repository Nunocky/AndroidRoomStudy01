package org.nunocky.roomstudy01

import android.app.Application
import kotlinx.coroutines.*
import org.nunocky.roomstudy01.database.TopicRepository

class MyApplication : Application() {
    private val scope = MainScope()

    override fun onCreate() {
        super.onCreate()

        // データベースを初期化する
        scope.launch(Dispatchers.IO) {
            val topicRepository = TopicRepository.instance()
            topicRepository.init(this@MyApplication)
        }
    }

}