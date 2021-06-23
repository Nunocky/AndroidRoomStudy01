package org.nunocky.sample2

import android.app.Application
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
        val tagRelation = appDatabase.getTopicTagRelationDAO()

        // 絞り込みタグの IDは固定
        appDatabase.getTagDAO().let { dao ->
            dao.insert(Tag(id = 1, title = "Tag 1"))
            dao.insert(Tag(id = 2, title = "Tag 2"))
            dao.insert(Tag(id = 3, title = "Tag 3"))
        }

        appDatabase.getTopicDAO().let { dao ->
            var topic: Topic = Topic()
            var rowId: Long = 0

            // Title1 は tag1, tag2と関連付け
            topic = Topic(title = "Title 1")
            rowId = dao.insert(topic)
            tagRelation.apply {
                insert(TopicTagRelation(topic_id = rowId, tag_id = 1))
                insert(TopicTagRelation(topic_id = rowId, tag_id = 2))
            }

            // Title2 は tag2, tag3と関連付け
            topic = Topic(title = "Title 2")
            rowId = dao.insert(topic)
            tagRelation.apply {
                insert(TopicTagRelation(topic_id = rowId, tag_id = 2))
                insert(TopicTagRelation(topic_id = rowId, tag_id = 3))
            }

            // Title3 は tag3, tag1と関連付け
            topic = Topic(title = "Title 3")
            rowId = dao.insert(topic)
            tagRelation.apply {
                insert(TopicTagRelation(topic_id = rowId, tag_id = 3))
                insert(TopicTagRelation(topic_id = rowId, tag_id = 1))
            }
        }
    }

}