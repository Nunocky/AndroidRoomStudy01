package org.nunocky.sample3.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import org.nunocky.sample3.database.Topic
import org.nunocky.sample3.databinding.TopicListitemBinding

class TopicListAdapter(private var list: List<Topic>) : BaseAdapter() {
    override fun getCount() = list.size

    override fun getItem(position: Int): Any {
        return list[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val binding = if (convertView == null) {
            val inflater = LayoutInflater.from(parent.context)
            TopicListitemBinding.inflate(inflater, parent, false).apply {
                root.tag = this
            }
        } else {
            convertView.tag as @androidx.annotation.NonNull TopicListitemBinding
        }

        with(binding) {
            item = list[position]
        }

        return binding.root
    }

    fun updateItems(newList: List<Topic>) {
        list = newList
        notifyDataSetInvalidated()
    }
}