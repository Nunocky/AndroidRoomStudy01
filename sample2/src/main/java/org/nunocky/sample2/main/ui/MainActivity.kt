package org.nunocky.sample2.main.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import org.nunocky.sample2.R
import org.nunocky.sample2.TopicRepository
import org.nunocky.sample2.adapters.TopicListAdapter
import org.nunocky.sample2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        binding.listView.adapter = TopicListAdapter(emptyList())

        binding.cbTag1.setOnCheckedChangeListener { _, isChecked ->
            val filter = viewModel.filter.value?.apply {
                tag1 = isChecked
            }

            viewModel.filter.value = filter
        }

        binding.cbTag2.setOnCheckedChangeListener { _, isChecked ->
            val filter = viewModel.filter.value?.apply {
                tag2 = isChecked
            }

            viewModel.filter.value = filter
        }

        binding.cbTag3.setOnCheckedChangeListener { _, isChecked ->
            val filter = viewModel.filter.value?.apply {
                tag3 = isChecked
            }

            viewModel.filter.value = filter
        }

        binding.cbFav.setOnCheckedChangeListener { _, isChecked ->
            val filter = viewModel.filter.value?.apply {
                fav = isChecked
            }

            viewModel.filter.value = filter
        }

        // 絞り込み条件の初期値
        viewModel.filter.value = TopicRepository.Filter(
            tag1 = true,
            tag2 = true,
            tag3 = true,
            fav = false
        )
    }
}