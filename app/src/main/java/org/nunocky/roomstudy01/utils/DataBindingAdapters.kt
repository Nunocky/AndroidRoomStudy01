package org.nunocky.roomstudy01.utils

import android.widget.ImageView
import android.widget.ListView
import androidx.databinding.BindingAdapter
import org.nunocky.roomstudy01.database.room.Topic

class DataBindingAdapters {
    companion object {
        @JvmStatic
        @BindingAdapter("imageResource")
        fun setImageResource(imageView: ImageView, resourceId: Int) {
            imageView.setImageResource(resourceId)
        }

//        @JvmStatic
//        @BindingAdapter("list")
//        fun setList(listView: ListView, newList: List<Topic>) {
//            listView.adapter = TopicListAdapter(newList)
//        }
    }
}