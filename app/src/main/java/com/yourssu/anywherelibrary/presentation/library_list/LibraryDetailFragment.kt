package com.yourssu.anywherelibrary.presentation.library_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.yourssu.anywherelibrary.R
import com.yourssu.anywherelibrary.databinding.FragmentLibraryDetailBinding

class LibraryDetailFragment: Fragment() {
    private lateinit var viewModel: LibraryDetailViewModel
    private lateinit var binding : FragmentLibraryDetailBinding

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
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_library_detail, container, false)
        viewModel =
            ViewModelProviders.of(this).get(LibraryDetailViewModel::class.java)
        binding.viewModel = viewModel
    }
}