package com.example.navigationfirstproject.ui.newsdetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import coil.load
import com.example.navigationfirstproject.R
import com.example.navigationfirstproject.RoomDatabase
import com.example.navigationfirstproject.databinding.FragmentThirdBinding
import com.example.navigationfirstproject.ui.news.SecondFragment
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


class ThirdFragment : Fragment(R.layout.fragment_third) {

    lateinit var binding: FragmentThirdBinding
    private val viewModel:ThirdFragmentViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding=FragmentThirdBinding.bind(view)

        observeNewsState()

        arguments?.getInt("KEY",-1)?.let { newsId->
            viewModel.getNewsById(newsId,RoomDatabase.getDatabase(requireContext()))
        }
        binding.btnGecis.setOnClickListener {
            findNavController().navigate(R.id.action_thirdFragment_to_authorFragment)
        }

    }

    private fun observeNewsState(){
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED){
                viewModel.newsState.collect{
                    it?.let {
                        binding.tvNewsTitle.text= it.title
                        binding.tvNewsContent.text= it.description
                        binding.tvNewsPostTime.text= it.postTime
                        binding.ivNewsImage.load(it.imageUrl)
                    }
                }
            }
        }
    }
}
