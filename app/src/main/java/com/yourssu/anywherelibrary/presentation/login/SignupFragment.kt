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
import com.yourssu.anywherelibrary.databinding.FragmentSignupBinding

class SignupFragment : Fragment() {
    private lateinit var signupViewModel: SignupViewModel
    private lateinit var binding : FragmentSignupBinding

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
        binding.signupSubmit.setOnClickListener {
            signupViewModel.doSignup(binding.signupId.text.toString(),
                binding.signupPassword.text.toString(),
                binding.signupNickname.text.toString(),
                binding.signupUniversity.text.toString())
        }
    }

    private fun setLiveDataObserver() {
        signupViewModel.signupSuccess.observe(this, Observer {
            findNavController().navigate(R.id.action_signupFragment_to_library_navigation)
        })
    }

    private fun setDataBinding(inflater: LayoutInflater, container: ViewGroup?) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_signup, container, false)
        signupViewModel =
            ViewModelProviders.of(this).get(SignupViewModel::class.java)
        binding.viewModel = signupViewModel
    }
}