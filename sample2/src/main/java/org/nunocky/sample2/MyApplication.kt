package org.nunocky.sample2

import android.app.Application
import android.util.Log
import androidx.room.Room
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import org.nunocky.sample2.database.*

class MyApplication : Application() {
    private val applicationScope = CoroutineScope(SupervisorJob() + Dispatchers.Main)

    lateinit var appDatabase: AppDatabase
    lateinit var repository: Repository

    override fun onCreate() {
        super.onCreate()

        appDatabase =
            Room.databaseBuilder(this@MyApplication, AppDatabase::class.java, "appDatabase")
                .fallbackToDestructiveMigration()
                .build()

        repository = Repository(appDatabase)

        applicationScope.launch(Dispatchers.IO) {
            // Topicテーブルの数が 0なら初期化する
            if (appDatabase.getTopicDAO().count() == 0) {
                initDatabase()
            }
        }
    }

    private fun initDatabase() {
        appDatabase.getTopicDAO().let { dao ->
            for (n in 1..3) {
                val topic = Topic(n, "Title $n")
                dao.insert(topic)
                Log.d("MyApplication", "new Item ${topic.id}")
            }
        }

        appDatabase.getTagDAO().let { dao ->
            dao.insert(Tag(1, "Tag 1"))
            dao.insert(Tag(2, "Tag 2"))
            dao.insert(Tag(3, "Tag 3"))
        }

        appDatabase.getTagRelationDAO().let { dao ->
            dao.insert(TagRelation(0, 1, 1))
            dao.insert(TagRelation(0, 1, 2))
            dao.insert(TagRelation(0, 2, 2))
            dao.insert(TagRelation(0, 2, 3))
            dao.insert(TagRelation(0, 3, 3))
            dao.insert(TagRelation(0, 3, 1))
        }
    }

}