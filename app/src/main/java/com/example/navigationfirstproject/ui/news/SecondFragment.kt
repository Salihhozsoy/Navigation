package com.example.navigationfirstproject.ui.news

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.navigationfirstproject.AdapterState
import com.example.navigationfirstproject.R
import com.example.navigationfirstproject.RoomDatabase
import com.example.navigationfirstproject.databinding.FragmentSecondBinding
import com.example.navigationfirstproject.entity.News
import kotlinx.coroutines.launch

class SecondFragment : Fragment(R.layout.fragment_second) {

    companion object {
        const val KEY = "key"
    }

    private lateinit var binding: FragmentSecondBinding
    private val viewModel: SecondFragmentViewModel by activityViewModels()
    private var adapter: NewsAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSecondBinding.bind(view)

        observeAdapterState()
        observeNewsListState()
        viewModel.getNews(RoomDatabase.getDatabase(requireContext()))
    }

    private fun observeAdapterState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED) {
                viewModel.adapterState.collect {
                    when (it) {
                        is AdapterState.Idle -> {}
                        is AdapterState.Removed -> {
                            adapter?.notifyItemRemoved(it.index)
                        }
                        else -> {
                        }
                    }
                }
            }
        }
    }
    private fun observeNewsListState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED) {
                viewModel.newsListState.collect {
                    when (it) {
                        is NewsListState.Idle -> {}
                        is NewsListState.Loading -> {}
                        is NewsListState.Result -> { adapter = NewsAdapter(requireContext(), it.news, this@SecondFragment::onClick, this@SecondFragment::onRemove)
                            binding.rvNews.adapter = adapter
                        }
                        is NewsListState.Empty -> {}
                        is NewsListState.Error -> {}
                    }
                }
            }
        }
    }

    private fun onClick(news: News) {
        findNavController().navigate(R.id.action_secondFragment_to_thirdFragment,bundleOf("KEY" to news.id))
    }

    private fun onRemove(news: News) {
        viewModel.removeNews(RoomDatabase.getDatabase(requireContext()),news)
    }
}