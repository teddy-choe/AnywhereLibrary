package com.yourssu.anywherelibrary.presentation.library_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.yourssu.anywherelibrary.R
import com.yourssu.anywherelibrary.databinding.FragmentLibraryListMainBinding
import com.yourssu.anywherelibrary.domain.entity.SimpleLibrary
import kotlinx.android.synthetic.main.fragment_library_list_main.*

class LibraryListFragment: Fragment() {
    private lateinit var viewModel: LibraryListViewModel
    private lateinit var binding : FragmentLibraryListMainBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setDataBinding(inflater, container)
        setLiveDataObserver()
        setListenerBinding()
        viewModel.getLibraryList()

        return binding.root
    }

    private fun setAdapter(libraryList : ArrayList<SimpleLibrary>) {
        with(main_recycler_view) {
            adapter = LibraryListAdapter(libraryList)
            layoutManager = GridLayoutManager(context,3)
        }
    }

    private fun setListenerBinding() {
    }

    private fun setLiveDataObserver() {
        viewModel.getListCall.observe(this, Observer {
            setAdapter(viewModel.libraries)
        })
    }

    private fun setDataBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_library_list_main, container, false)
        viewModel =
            ViewModelProviders.of(this).get(LibraryListViewModel::class.java)
        binding.viewModel = viewModel
    }
}