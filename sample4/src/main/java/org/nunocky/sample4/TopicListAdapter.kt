package org.nunocky.sample4

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.bumptech.glide.Glide
import org.nunocky.sample4.database.Topic
import org.nunocky.sample4.databinding.TopicListitemBinding

class TopicListAdapter(private val context: Context, var list: List<Topic>) : BaseAdapter() {
    fun updateList(newList: List<Topic>) {
        list = newList
        notifyDataSetChanged()
    }

    override fun getCount() = list.size
    override fun getItem(position: Int) = list[position]
    override fun getItemId(position: Int) = list[position].id

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val binding = if (convertView == null) {
            val inflater = LayoutInflater.from(parent.context)
            TopicListitemBinding.inflate(inflater, parent, false).apply {
                root.tag = this
            }
        } else {
            convertView.tag as @androidx.annotation.NonNull TopicListitemBinding
        }

        val current = list[position]
        binding.topic = current
        Glide.with(context).load(current.thumbnailUri).into(binding.ivImage)

        return binding.root
    }
}