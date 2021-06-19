package org.nunocky.roomstudy01.ui.main

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.nunocky.roomstudy01.MyApplication
import org.nunocky.roomstudy01.R
import org.nunocky.roomstudy01.database.TopicRepository
import org.nunocky.roomstudy01.database.room.Topic
import org.nunocky.roomstudy01.databinding.MainFragmentBinding
import org.nunocky.roomstudy01.view.TopicListAdapter


class MainFragment : Fragment() {
    enum class ORDERBY {
        CREATED_AT,
        UPDATED_AT
    }

    enum class ORDER {
        ASC,
        DESC
    }

    private lateinit var topicRepository: TopicRepository

    private lateinit var binding: MainFragmentBinding
    private val viewModel: MainViewModel by viewModels {
        topicRepository = (requireActivity().application as MyApplication).topicRepository
        MainViewModel.Factory(topicRepository)
    }

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
        setHasOptionsMenu(true);

        val adapter = TopicListAdapter(emptyList())

        binding.listView.adapter = adapter
        adapter.listener = object : TopicListAdapter.Listener {
            override fun onFavButtonClicked(topic: Topic) {
                toggleFav(topic)
            }
        }

        binding.listView.setOnItemClickListener { _, _, position, _ ->
            val topic = adapter.getItem(position) as Topic
            val action = MainFragmentDirections.actionMainFragmentToEditFragment(topic)
            findNavController().navigate(action)
        }

        binding.listView.setOnItemLongClickListener { _, _, position, _ ->
            val topic = adapter.getItem(position) as Topic
            AlertDialog.Builder(activity)
                .setTitle("Delete")
                //.setMessage("message")
                .setPositiveButton("Delete") { _, _ ->
                    deleteTopic(topic)
                }
                .setNegativeButton("Cancel", null)
                .show()

            true
        }

        binding.fab.setOnClickListener {
            findNavController().navigate(R.id.newItemFragment)
        }
    }

    override fun onResume() {
        super.onResume()
        reloadTopicList()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.main_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_create_asc -> {
                orderBy = ORDERBY.CREATED_AT
                order = ORDER.ASC
                reloadTopicList()
            }
            R.id.menu_create_desc -> {
                orderBy = ORDERBY.CREATED_AT
                order = ORDER.DESC
                reloadTopicList()
            }
            R.id.menu_update_asc -> {
                orderBy = ORDERBY.UPDATED_AT
                order = ORDER.ASC
                reloadTopicList()
            }
            R.id.menu_update_desc -> {
                orderBy = ORDERBY.UPDATED_AT
                order = ORDER.DESC
                reloadTopicList()
            }
            R.id.menu_show_only_favorites -> {
                item.isChecked = !item.isChecked
                onlyFavorites = item.isChecked
                reloadTopicList()
            }
        }

        return super.onOptionsItemSelected(item)
    }

    private val listObserver = Observer<List<Topic>> { newList ->
        val adapter = (binding.listView.adapter as TopicListAdapter)
        adapter.updateItems(newList)
        adapter.notifyDataSetInvalidated()
    }

    private fun registerTopicListObserver() {
        val adapter = (binding.listView.adapter as TopicListAdapter)

        viewModel.topicList.observe(requireActivity(), listObserver)
        adapter.notifyDataSetInvalidated()
    }

    private fun unregisterTopicListObserver() {
        viewModel.topicList.removeObserver(listObserver)

        val adapter = (binding.listView.adapter as TopicListAdapter)
        adapter.notifyDataSetInvalidated()
    }

    private var orderBy = ORDERBY.CREATED_AT
    private var order = ORDER.ASC
    private var onlyFavorites = false

    private fun toggleFav(topic: Topic) {
        lifecycleScope.launch(Dispatchers.IO) {
            topic.fav = !topic.fav
            topicRepository.update(topic)
        }
    }

    private fun deleteTopic(topic: Topic) {
        lifecycleScope.launch(Dispatchers.IO) {
            topicRepository.delete(topic)
        }
    }

    private fun reloadTopicList() {
        lifecycleScope.launch(Dispatchers.IO) {
            launch(Dispatchers.Main) {
                unregisterTopicListObserver()
            }.join()

            val isAsc = order == ORDER.ASC

            viewModel.topicList = if (orderBy == ORDERBY.CREATED_AT) {
                if (onlyFavorites) {
                    topicRepository.getAllFavoritesWithCreatedAt(isAsc)
                } else {
                    topicRepository.getAllWithCreatedAt(isAsc)
                }
            } else {
                if (onlyFavorites) {
                    topicRepository.getAllFavoritesWithUpdatedAt(isAsc)
                } else {
                    topicRepository.getAllWithUpdatedAt(isAsc)
                }
            }

            launch(Dispatchers.Main) {
                registerTopicListObserver()
            }.join()
        }
    }
}