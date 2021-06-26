package org.nunocky.sample3.adapter

import android.widget.ListView
import androidx.databinding.BindingAdapter
import org.nunocky.sample3.database.Text
import org.nunocky.sample3.database.Topic

class DataBindingAdapter {
    companion object {
        @JvmStatic
        @BindingAdapter("topicList")
        fun setTopicList(listView: ListView, list: List<Topic>?) {
            list?.let {
                (listView.adapter as TopicListAdapter).updateItems(list)
            }
        }

        @JvmStatic
        @BindingAdapter("textList")
        fun setTextList(listView: ListView, list: List<Text>?) {
            list?.let {
                (listView.adapter as TextListAdapter).updateItems(list)
            }
        }
    }
}