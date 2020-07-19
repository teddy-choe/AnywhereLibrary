package com.yourssu.anywherelibrary.presentation.library_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.yourssu.anywherelibrary.R
import com.yourssu.anywherelibrary.databinding.FragmentLibraryListMainBinding
import com.yourssu.anywherelibrary.domain.entity.SimpleLibrary
import com.yourssu.anywherelibrary.domain.entity.UniversityRanking
import com.yourssu.anywherelibrary.util.ConstValue
import com.yourssu.anywherelibrary.util.SharedPreferenceManager
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
        viewModel.getRankingList()

        binding.libraryMainUser.text = "공부하기 좋은 날이에요, ${SharedPreferenceManager.getStringPref(ConstValue.CONST_USER_NICKNAME)}님!"

        return binding.root
    }

    private fun setAdapter(libraryList : ArrayList<SimpleLibrary>) {
        with(list_recycler_view) {
            adapter = LibraryListAdapter(libraryList, this@LibraryListFragment)
            layoutManager = GridLayoutManager(context,3)
        }
    }

    private fun setRankAdapter(rankList : ArrayList<UniversityRanking>) {
        with(ranking_recycler_view) {
            adapter = LibraryRankingAdapter(rankList)
            layoutManager = LinearLayoutManager(context)
        }
    }

    private fun setListenerBinding() {
    }

    private fun setLiveDataObserver() {
        viewModel.getListCall.observe(this, Observer {
            setAdapter(viewModel.libraries)
        })

        viewModel.getRankingListCall.observe(this, Observer {
            setRankAdapter(viewModel.rakingList)
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

    fun startDetailFragment(library: SimpleLibrary) {
        findNavController().navigate(R.id.action_libraryListFragment_to_libraryDetailFragment, bundleOf("libraryId" to library.id,
            "isHangout" to library.hangout))
    }

    fun startPostFragment() {
        findNavController().navigate(R.id.action_libraryListFragment_to_libraryPostFragment)
    }
}