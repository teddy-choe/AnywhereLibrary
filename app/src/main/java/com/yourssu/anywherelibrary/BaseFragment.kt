package com.yourssu.anywherelibrary

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.yourssu.anywherelibrary.databinding.FragmentLoginBinding
import com.yourssu.anywherelibrary.presentation.login.LoginViewModel

class BaseFragment : Fragment() {
    private lateinit var viewModel: LoginViewModel
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
    }

    private fun setLiveDataObserver() {
    }

    private fun setDataBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)
        viewModel =
            ViewModelProviders.of(this).get(LoginViewModel::class.java)
        binding.viewModel = viewModel
    }
}