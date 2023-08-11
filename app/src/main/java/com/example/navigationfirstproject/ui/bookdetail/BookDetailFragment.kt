package com.example.navigationfirstproject.ui.bookdetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.navigationfirstproject.R
import com.example.navigationfirstproject.RoomDatabase
import com.example.navigationfirstproject.databinding.FragmentBookDetailBinding
import com.example.navigationfirstproject.ui.book.GetAuthorState
import kotlinx.coroutines.launch


class BookDetailFragment : Fragment(R.layout.fragment_book_detail) {

    lateinit var binding: FragmentBookDetailBinding
    private val viewModel:BookDetailViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding= FragmentBookDetailBinding.bind(view)

        observeGetBookAuthorState()

        viewModel.getBooks(RoomDatabase.getDatabase(requireContext()))
    }

        private fun observeGetBookAuthorState() {
            lifecycleScope.launch {
                repeatOnLifecycle(Lifecycle.State.CREATED) {
                    viewModel.getBookAuthorState.collect {
                        when (it) {
                            GetBookAuthorState.Idle -> {}
                            GetBookAuthorState.Empty -> {}
                            is GetBookAuthorState.Success -> {
                            binding.rvBooks.adapter=BookAuthorAdapter(requireContext(),it.books)
                            }
                            GetBookAuthorState.Error -> {}
                        }
                    }
                }
            }
        }
    }
