package org.nunocky.sample2.adapters

import android.widget.ListView
import androidx.databinding.BindingAdapter
import org.nunocky.sample2.database.Topic

class DataBindingAdapters {
    companion object {
        @JvmStatic
        @BindingAdapter("items")
        fun setList(listView: ListView, newList: List<Topic>?) {
            if (newList != null) {
                val adapter = listView.adapter as TopicListAdapter
                adapter.updateItems(newList)
            }
        }
    }
}