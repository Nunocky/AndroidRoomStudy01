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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.nunocky.roomstudy01.MyApplication
import org.nunocky.roomstudy01.database.room.Topic
import org.nunocky.roomstudy01.databinding.NewItemFragmentBinding
import java.util.*

class NewItemFragment : Fragment() {

    private val viewModel: NewItemViewModel by viewModels()
//    {
//        val repository = (requireActivity().application as MyApplication).topicRepository
//        NewItemViewModel.Factory(repository)
//    }

    private lateinit var binding: NewItemFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = NewItemFragmentBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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

        binding.btnRegister.setOnClickListener {
            registerNewItem(binding.etTitle.text.toString())
        }
    }

    private fun registerNewItem(title: String) {
        viewLifecycleOwner.lifecycleScope.launch(Dispatchers.IO) {
            val repository = (requireActivity().application as MyApplication).topicRepository
            val entry = Topic(0, title, false, Date(), Date())
            repository.insert(entry)

            launch(Dispatchers.Main) {
                findNavController().popBackStack()
            }
        }
    }

}