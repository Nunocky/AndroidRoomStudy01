package org.nunocky.roomstudy01

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import org.nunocky.roomstudy01.databinding.MainFragmentBinding


class MainFragment : Fragment() {
    private lateinit var binding: MainFragmentBinding
    private val viewModel: MainViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MainFragmentBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = TopicListAdapter(emptyList())

        binding.listView.adapter = adapter
        adapter.listener = object : TopicListAdapter.Listener {
            override fun onFavButtonClicked(id: Int) {
                viewModel.toggleFav(id)
            }
        }

        viewModel.topicList.observe(requireActivity()) { newList ->
            (binding.listView.adapter as TopicListAdapter).updateItems(newList)
        }

        binding.listView.setOnItemLongClickListener { _, _, position, _ ->
            AlertDialog.Builder(activity)
                .setTitle("Delete")
                //.setMessage("message")
                .setPositiveButton("Delete") { _, _ ->
                    viewModel.deleteItem(position)
                }
                .setNegativeButton("Cancel", null)
                .show()

            false
        }

        binding.fab.setOnClickListener {
            findNavController().navigate(R.id.newItemFragment)
        }
    }
}