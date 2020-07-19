package com.yourssu.anywherelibrary.presentation.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.yourssu.anywherelibrary.R
import com.yourssu.anywherelibrary.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {
    private lateinit var loginViewModel: LoginViewModel
    private lateinit var binding : FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        setDataBinding(inflater, container)
        setLiveDataObserver()
        setListenerBinding()

        return binding.root
    }

    private fun setListenerBinding() {
        binding.loginSubmit.setOnClickListener {
            loginViewModel.doLogin(binding.loginEtId.text.toString(),
                binding.loginEtPassword.text.toString())
        }
    }

    private fun setLiveDataObserver() {
        loginViewModel.loginSuccess.observe(this, Observer {
            findNavController().navigate(R.id.action_loginFragment_to_library_navigation)
        })
    }

    private fun setDataBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)
        loginViewModel =
            ViewModelProviders.of(this).get(LoginViewModel::class.java)
        binding.viewModel = loginViewModel
        binding.loginToSignup.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_signupFragment)
        }
    }
}