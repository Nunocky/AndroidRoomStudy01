package org.nunocky.sample3.adapter

import android.widget.ListView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import org.nunocky.sample3.database.Text
import org.nunocky.sample3.database.Topic
import org.nunocky.sample3.database.TopicAndTexts

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

        @JvmStatic
        @BindingAdapter("topicAndTexts")
        fun setTextList(textView: TextView, entity: TopicAndTexts?) {
            entity?.let {
                val sb = StringBuffer()
                sb.appendLine("Topic : ${it.topic.title}")
                it.texts.forEach { text ->
                    sb.appendLine("  Text : ${text.text}")
                }

                textView.text = sb.toString()
            }
        }
    }
}