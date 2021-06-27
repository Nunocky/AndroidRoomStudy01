package org.nunocky.sample4

import android.widget.ListView
import androidx.databinding.BindingAdapter
import org.nunocky.sample4.database.Topic

class DataBindingAdapter {
    companion object {
        @JvmStatic
        @BindingAdapter("topics")
        fun setTopicList(listView: ListView, list: List<Topic>?) {
            list?.let {
                (listView.adapter as TopicListAdapter).updateList(list)
            }
        }
    }
}