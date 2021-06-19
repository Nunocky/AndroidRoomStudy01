package org.nunocky.roomstudy01.ui.main

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.nunocky.roomstudy01.MyApplication
import org.nunocky.roomstudy01.database.room.Topic
import org.nunocky.roomstudy01.databinding.EditFragmentBinding
import java.util.*

class EditFragment : Fragment() {

    private val viewModel: EditViewModel by viewModels()
    private lateinit var binding: EditFragmentBinding
    private val args: EditFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = EditFragmentBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.title.value = args.topic.title

        binding.etTitle.doOnTextChanged { text, _, _, _ ->
            viewModel.ready.value = text?.isNotEmpty()
        }

        binding.etTitle.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                val manager =
                    activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                manager.hideSoftInputFromWindow(binding.etTitle.windowToken, 0)
            }
        }

        binding.btnUpdate.setOnClickListener {
            args.topic.let { topic ->
                topic.title = viewModel.title.value ?: ""
                topic.updatedAt = Date()
                updateTopic(topic)
            }
        }

    }

    private fun updateTopic(topic: Topic) {

        viewLifecycleOwner.lifecycleScope.launch(Dispatchers.IO) {
            val repository = (requireActivity().application as MyApplication).topicRepository
            repository.update(topic)
            launch(Dispatchers.Main) {
                findNavController().popBackStack()
            }
        }
    }

}