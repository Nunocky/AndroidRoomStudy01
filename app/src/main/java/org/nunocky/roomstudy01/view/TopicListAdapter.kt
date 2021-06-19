package org.nunocky.roomstudy01.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.annotation.NonNull
import org.nunocky.roomstudy01.database.room.Topic
import org.nunocky.roomstudy01.databinding.TopicItemBinding

class TopicListAdapter(
    private var topicList: List<Topic>
) : BaseAdapter() {
    interface Listener {
        fun onFavButtonClicked(topic: Topic)
    }

    var listener: Listener? = null

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
            convertView.tag as @NonNull TopicItemBinding
        }

        with(binding) {
            topic = topicList[position]
            imageView.setOnClickListener {
                listener?.onFavButtonClicked(topicList[position])
            }
        }

        return binding.root
    }
}