package com.example.navigationfirstproject.ui.login

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
import com.example.navigationfirstproject.databinding.FragmentLoginBinding
import kotlinx.coroutines.launch


class LoginFragment : Fragment(R.layout.fragment_login) {

    lateinit var binding :FragmentLoginBinding
    private val viewModel: LoginFragmentViewModel by activityViewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentLoginBinding.bind(view)

        observeLoginState()

        binding.btnLogin.setOnClickListener {
            viewModel.login(binding.etEmail.text.toString(),binding.etPassword.text.toString(), RoomDatabase.getDatabase(requireContext()))
        }

    }
   private fun observeLoginState(){
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED){
                viewModel.loginState.collect{
                    when(it){
                        is LoginState.Idle ->{}
                        is LoginState.Success ->{
                            findNavController().navigate(R.id.action_homeFragment_to_secondFragment)
                        }
                        is LoginState.UserNotFound ->{}
                        is LoginState.Error ->{}
                    }
                }
            }
        }
    }
}