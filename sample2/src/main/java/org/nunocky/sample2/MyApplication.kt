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
    lateinit var repository: TopicRepository

    override fun onCreate() {
        super.onCreate()

        appDatabase =
            Room.databaseBuilder(this@MyApplication, AppDatabase::class.java, "appDatabase")
                .fallbackToDestructiveMigration()
                .build()

        repository = TopicRepository(appDatabase)

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
            var topic: Topic
            var rowId: Long

            // Title1 は tag1, tag2と関連付け
            topic = Topic(title = "Title 1 (#Tag1 #Tag2)")
            rowId = dao.insert(topic)
            tagRelation.apply {
                insert(TopicTagRelation(topic_id = rowId, tag_id = 1))
                insert(TopicTagRelation(topic_id = rowId, tag_id = 2))
            }

            // Title2 は tag2, tag3と関連付け
            topic = Topic(title = "Title 2 (#Tag2 #Tag3)")
            rowId = dao.insert(topic)
            tagRelation.apply {
                insert(TopicTagRelation(topic_id = rowId, tag_id = 2))
                insert(TopicTagRelation(topic_id = rowId, tag_id = 3))
            }

            // Title3 は tag3, tag1と関連付け
            topic = Topic(title = "Title 3 (#Tag1 #Tag3)")
            rowId = dao.insert(topic)
            tagRelation.apply {
                insert(TopicTagRelation(topic_id = rowId, tag_id = 3))
                insert(TopicTagRelation(topic_id = rowId, tag_id = 1))
            }

            // Title4 は tag1, tag2, tag3と関連付け, fav設定
            topic = Topic(title = "Title 4 (#Tag1 #Tag2 #Tag3, fav)", fav = true)
            rowId = dao.insert(topic)
            tagRelation.apply {
                insert(TopicTagRelation(topic_id = rowId, tag_id = 1))
                insert(TopicTagRelation(topic_id = rowId, tag_id = 2))
                insert(TopicTagRelation(topic_id = rowId, tag_id = 3))
            }
        }
    }

}