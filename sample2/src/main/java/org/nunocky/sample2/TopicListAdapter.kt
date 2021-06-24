package org.nunocky.sample2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import org.nunocky.sample2.database.Topic
import org.nunocky.sample2.databinding.TopicItemBinding

class TopicListAdapter(
    private var topicList: List<Topic>
) : BaseAdapter() {

    fun updateItems(newList: List<Topic>) {
        topicList = newList
        notifyDataSetInvalidated()
    }

    override fun getCount(): Int {
        return topicList.size
    }

    override fun getItem(position: Int): Any {
        return topicList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val binding = if (convertView == null) {
            val inflater = LayoutInflater.from(parent.context)
            TopicItemBinding.inflate(inflater, parent, false).apply {
                root.tag = this
            }
        } else {
            convertView.tag as @androidx.annotation.NonNull TopicItemBinding
        }

        with(binding) {
            topic = topicList[position]
        }

        return binding.root
    }
}