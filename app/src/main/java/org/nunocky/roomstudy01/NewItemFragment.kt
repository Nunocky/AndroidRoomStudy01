package org.nunocky.roomstudy01

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import org.nunocky.roomstudy01.databinding.NewItemFragmentBinding

class NewItemFragment : Fragment() {

    private val viewModel: NewItemViewModel by viewModels()
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

        binding.etTitle.doOnTextChanged { text, start, before, count ->
            viewModel.ready.value = text?.isNotEmpty()
        }

        binding.etTitle.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                val manager =
                    activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                manager.hideSoftInputFromWindow(binding.etTitle.windowToken, 0)
            }
        }

        viewModel.status.observe(requireActivity()) {
            if (it == NewItemViewModel.Status.DONE) {
                viewModel.status.value = NewItemViewModel.Status.INIT
                findNavController().popBackStack()
            }
        }

        binding.btnRegister.setOnClickListener {
            viewModel.registerNewItem(binding.etTitle.text.toString())
        }
    }
}