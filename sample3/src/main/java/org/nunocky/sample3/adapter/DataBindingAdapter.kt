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
            textView.text =
                if (entity != null) {
                    val sb = StringBuffer()
                    sb.appendLine("Topic : ${entity.topic.title}")
                    entity.texts.forEach { text ->
                        sb.appendLine("  Text : ${text.text}")
                    }

                    sb.toString()
                } else {
                    ""
                }
        }
    }
}