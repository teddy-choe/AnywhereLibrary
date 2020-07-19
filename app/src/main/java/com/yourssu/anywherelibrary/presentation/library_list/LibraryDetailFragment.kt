package com.yourssu.anywherelibrary.presentation.library_list

import android.os.Bundle
import android.text.Html
import android.text.method.LinkMovementMethod
import android.text.util.Linkify
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.yourssu.anywherelibrary.R
import com.yourssu.anywherelibrary.databinding.FragmentLibraryDetailBinding
import com.yourssu.anywherelibrary.domain.entity.SimpleLibrary
import com.yourssu.anywherelibrary.domain.entity.SimpleSeat
import kotlinx.android.synthetic.main.fragment_library_detail.*
import org.jetbrains.anko.sdk27.coroutines.onClick
import java.util.regex.Pattern

class LibraryDetailFragment: Fragment() {
    private lateinit var viewModel: LibraryDetailViewModel
    private lateinit var binding : FragmentLibraryDetailBinding
    var libraryId : Long = 0
    var seatId: Long = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setDataBinding(inflater, container)
        setLiveDataObserver()
        setListenerBinding()

        val transform = Linkify.TransformFilter { match, url ->
            ""
        }

        val pattern = Pattern.compile("행아웃")

        if (arguments?.getString("isHangout") != null) {
            binding.detailHangoutBox.visibility = View.VISIBLE
            Linkify.addLinks(binding.detailHangoutBox, pattern, "https://hangouts.google.com/call/jgkv7X6QaWo78XTYdMz6ACEM", null, transform)
        }

        if (arguments?.getLong("libraryId") != null) {
            libraryId = arguments?.getLong("libraryId")!!
        }

        Log.d("DetailBundle", arguments?.getLong("libraryId").toString())
        arguments?.getLong("libraryId")?.let { viewModel.getDetailLibrary(it) }

        return binding.root
    }

    private fun setAdapter(seatList : ArrayList<SimpleSeat>, totalCount: Int) {
        with(detail_recycler_view) {
            Log.d("DetailSeatList", seatList.toString())
            adapter = LibraryDetailAdapter(seatList, totalCount, this@LibraryDetailFragment)
            layoutManager = GridLayoutManager(context,3)
        }
    }

    private fun setListenerBinding() {
        binding.detailIcBack.onClick {
            backListFragment()
        }
    }

    private fun setLiveDataObserver() {
        viewModel.getDetailSuccess.observe(this, Observer {
            setAdapter(viewModel.seatList, viewModel.totalCount)
        })
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

    fun backListFragment() {
        findNavController().navigateUp()
    }

    fun startSeatFragment(seatId : Long) {
        findNavController().navigate(R.id.action_libraryDetailFragment_to_seatFragment, bundleOf("libraryId" to libraryId,
            "seatId" to seatId)
        )
    }
}