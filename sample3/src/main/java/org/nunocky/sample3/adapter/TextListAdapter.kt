package org.nunocky.sample3.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import org.nunocky.sample3.database.Text
import org.nunocky.sample3.databinding.TextListitemBinding

class TextListAdapter(private var list: List<Text>) : BaseAdapter() {
    override fun getCount() = list.size

    override fun getItem(position: Int) = list[position]

    override fun getItemId(position: Int) = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val binding = if (convertView == null) {
            val inflater = LayoutInflater.from(parent.context)
            TextListitemBinding.inflate(inflater, parent, false).apply {
                root.tag = this
            }
        } else {
            convertView.tag as @androidx.annotation.NonNull TextListitemBinding
        }

        binding.item = list[position]

        return binding.root
    }

    fun updateItems(newList: List<Text>) {
        list = newList
        notifyDataSetInvalidated()
    }
}