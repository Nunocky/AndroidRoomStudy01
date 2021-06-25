package org.nunocky.sample2

import android.content.Context
import android.os.Environment
import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.nunocky.sample2.database.AppDatabase
import org.nunocky.sample2.database.Tag
import org.nunocky.sample2.database.Topic
import org.nunocky.sample2.database.TopicTagRelation
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class DatabaseInstrumentedTest {
    companion object {
        private const val TAG = "DatabaseInstrumentedTest"
    }

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("org.nunocky.sample2", appContext.packageName)
    }


    @Test
    fun databaseExport() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext

        val currentDBPath: String = appContext.getDatabasePath("appDatabase").absolutePath

        arrayOf("", "-shm", "-wal").forEach { postfix ->
            val srcPath = currentDBPath + postfix
            val filename = File(srcPath).name
            FileInputStream(File(srcPath)).use { iStream ->
                FileOutputStream(
                    File(
                        appContext.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS),
                        filename
                    )
                ).use { oStream ->
                    var len = 0
                    val buffer = ByteArray(8 * 1024)
                    do {
                        len = iStream.read(buffer, 0, buffer.size)
                        if (0 < len) {
                            oStream.write(buffer, 0, len)
                        }
                    } while (0 < len)
                }
            }
        }


    }


    private fun initDatabase(appContext: Context) {
        val appDatabase =
            Room.databaseBuilder(appContext, AppDatabase::class.java, "appDatabase")
                .fallbackToDestructiveMigration()
                .build()

        val tagRelation = appDatabase.getTopicTagRelationDAO()

        if (0 < appDatabase.getTopicDAO().count()) {
            return
        }

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