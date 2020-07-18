package com.yourssu.anywherelibrary.presentation.library_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.yourssu.anywherelibrary.R
import com.yourssu.anywherelibrary.databinding.FragmentLibraryListPostBinding

class LibraryPostFragment: Fragment() {
    private lateinit var viewModel: LibraryPostViewModel
    private lateinit var binding : FragmentLibraryListPostBinding

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
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_library_list_post, container, false)
        viewModel =
            ViewModelProviders.of(this).get(LibraryPostViewModel::class.java)
        binding.viewModel = viewModel
    }
}