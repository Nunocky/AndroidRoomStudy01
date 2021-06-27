package org.nunocky.sample4

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import org.nunocky.sample4.database.TopicRepository
import org.nunocky.sample4.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel: MainViewModel by viewModels {
        val app = (application as MyApplication)
        val appDatabase = app.database
        MainViewModel.Factory(app, TopicRepository(appDatabase))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        val adapter = TopicListAdapter(this, emptyList())
        binding.listView.adapter = adapter

        binding.listView.setOnItemClickListener { parent, view, position, id ->
            val topic = adapter.list[position]
            viewModel.current.value = topic
        }

        viewModel.topics.observe(this) {
            adapter.updateList(it)
        }

        viewModel.current.observe(this) {
            Glide.with(this).load(it.imageUri).into(binding.imageView)
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.initDatabase()
    }
}

