package org.nunocky.sample3

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import org.nunocky.sample3.adapter.TextListAdapter
import org.nunocky.sample3.adapter.TopicListAdapter
import org.nunocky.sample3.database.Text
import org.nunocky.sample3.database.Topic
import org.nunocky.sample3.databinding.ActivitySample3Binding
import kotlin.random.Random

class Sample3Activity : AppCompatActivity() {
    private lateinit var binding: ActivitySample3Binding

    private val viewModel: Sample3ViewModel by viewModels {
        val appDatabase = (application as MyApplication).appDatabase
        Sample3ViewModel.Factory(TitleTopicRepository(appDatabase))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_sample3)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        val topicAdapter = TopicListAdapter(emptyList())
        val allTextListAdapter = TextListAdapter(emptyList())
        val relatedTextListAdapter = TextListAdapter(emptyList())

        binding.lvTopics.adapter = topicAdapter
        binding.lvAllTexts.adapter = allTextListAdapter
        //binding.lvRelatedTexts.adapter = relatedTextListAdapter

        binding.lvTopics.setOnItemClickListener { _, _, position, _ ->
            val topic = topicAdapter.getItem(position) as Topic
            viewModel.topic.value = topic
        }

        binding.button.setOnClickListener {
            addNewItem()
        }

        binding.button2.setOnClickListener {
            deleteSelectedItem()
        }
    }

    private val rand = Random(0)

    private fun addNewItem() {
        val topic = Topic(0, "Topic " + rand.nextInt(0, 100))
        val text1 = Text(0, "Text " + rand.nextInt(0, 100))
        val text2 = Text(0, "Text " + rand.nextInt(0, 100))

        viewModel.addNewItem(topic, text1, text2)
    }

    private fun deleteSelectedItem() {
        viewModel.topic.value?.let {
            lifecycleScope.launch {
                viewModel.deleteItem(it).join()
                // TODO リストビューの操作。削除後に別のアイテムを選択、リストビューにも反映させる
            }
        }
    }
}