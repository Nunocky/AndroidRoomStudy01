package org.nunocky.roomstudy01.utils

import android.widget.ImageView
import android.widget.ListView
import androidx.databinding.BindingAdapter
import org.nunocky.roomstudy01.database.room.Topic
import org.nunocky.roomstudy01.view.TopicListAdapter

class DataBindingAdapters {
    companion object {
        @JvmStatic
        @BindingAdapter("imageResource")
        fun setImageResource(imageView: ImageView, resourceId: Int) {
            imageView.setImageResource(resourceId)
        }

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