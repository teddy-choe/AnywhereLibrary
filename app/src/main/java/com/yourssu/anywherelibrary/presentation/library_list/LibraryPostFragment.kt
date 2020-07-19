package com.yourssu.anywherelibrary.presentation.library_list

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
import com.yourssu.anywherelibrary.databinding.FragmentLibraryListPostBinding
import kotlinx.android.synthetic.main.fragment_library_list_post.*
import org.jetbrains.anko.backgroundColor
import org.jetbrains.anko.sdk27.coroutines.onClick

class LibraryPostFragment: Fragment() {
    private lateinit var viewModel: LibraryPostViewModel
    private lateinit var binding : FragmentLibraryListPostBinding

    private var personCount : Int = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setDataBinding(inflater, container)
        setLiveDataObserver()
        setListenerBinding()
        selectSeatCount()

        return binding.root
    }

    fun selectSeatCount() {
        binding.libraryPostCountSix.onClick {
            library_post_count_six.backgroundColor = resources.getColor(R.color.colorWarmGrey)
            library_post_count_twelve.backgroundColor = resources.getColor(R.color.colorVeryLightPink)
            personCount = 6
        }

        binding.libraryPostCountTwelve.onClick {
            library_post_count_six.backgroundColor = resources.getColor(R.color.colorVeryLightPink)
            library_post_count_twelve.backgroundColor = resources.getColor(R.color.colorWarmGrey)
            personCount = 12
        }
    }

    private fun setListenerBinding() {
        binding.postIcBack.onClick {
            backListFragment()
        }

        binding.postIcCheck.onClick {
            viewModel.postLibrary(library_post_hangout_link.text.toString(),
                library_post_name.text.toString(), personCount
                )
        }
    }

    private fun setLiveDataObserver() {
        viewModel.postSuccess.observe(this, Observer {
            backListFragment()
        })
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

    fun backListFragment() {
        findNavController().navigate(R.id.action_libraryPostFragment_to_libraryListFragment)
    }
}