package com.example.navigationfirstproject.ui.book

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.navigationfirstproject.R
import com.example.navigationfirstproject.RoomDatabase
import com.example.navigationfirstproject.databinding.FragmentBookBinding
import kotlinx.coroutines.launch

class BookFragment : Fragment(R.layout.fragment_book) {

    lateinit var binding: FragmentBookBinding
    private val viewModel: BookFragmentViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentBookBinding.bind(view)

        observeBookState()
        observeGetAuthorState()

        viewModel.getAuthors(RoomDatabase.getDatabase(requireContext()))

        binding.btnAddBook.setOnClickListener {
            viewModel.addBook(
                RoomDatabase.getDatabase(requireContext()),
                binding.etBookName.text.toString(),
                binding.etPageNumber.text.toString(),
                binding.etBookPublisher.text.toString(),
            )
        }
        binding.spAuthors.onItemSelectedListener =object :OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                viewModel.authorSelected(position)
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {

            }
        }

        binding.btnKitapListesineGecis.setOnClickListener{
            findNavController().navigate(R.id.action_bookFragment_to_bookDetailFragment)
        }
    }

    private fun observeBookState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED) {
                viewModel.bookState.collect {
                    when (it) {
                        BookState.Idle -> {}
                        BookState.Success -> {
                            AlertDialog.Builder(context).setMessage("Kitap Eklendi").create().show()
                        }
                        BookState.Error -> {}
                    }
                }
            }
        }
    }

    private fun observeGetAuthorState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED) {
                viewModel.getAuthorState.collect {
                    when (it) {
                        GetAuthorState.Idle -> {}
                        GetAuthorState.Empty -> {}
                        is GetAuthorState.Success -> {
                            binding.spAuthors.adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1,it.author.map {"${it.name} ${it.surname}" })
                        }

                        is GetAuthorState.Error -> {}
                    }
                }
            }
        }
    }
}