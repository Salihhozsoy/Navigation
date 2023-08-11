package com.example.navigationfirstproject.ui.author

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.navigationfirstproject.R
import com.example.navigationfirstproject.RoomDatabase
import com.example.navigationfirstproject.databinding.FragmentAuthorBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


class AuthorFragment : Fragment(R.layout.fragment_author) {
    lateinit var binding: FragmentAuthorBinding
    private val viewModel:AuthorFragmentViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAuthorBinding.bind(view)

        observeAuthorState()
        binding.btnSave.setOnClickListener {
            viewModel.addAuthor(RoomDatabase.getDatabase(requireContext()),binding.etAuthorName.text.toString(),binding.etAuthorSurname.text.toString())
        }
        binding.btnKitaplaraGecis.setOnClickListener {
            findNavController().navigate(R.id.action_authorFragment_to_bookFragment)
        }
    }

    private fun observeAuthorState(){
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED){
                viewModel.authorState.collect{
                    when(it){
                        AuthorState.Idle->{}
                        is AuthorState.Success->{
                            AlertDialog.Builder(requireContext()).setTitle("Başarılı").setMessage("Yazar ekleme başarılı").create().show()
                        }
                        is AuthorState.Error->{}
                    }
                }
            }
        }
    }

}