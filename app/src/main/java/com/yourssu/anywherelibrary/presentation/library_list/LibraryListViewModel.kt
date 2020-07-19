package com.yourssu.anywherelibrary.presentation.library_list

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yourssu.anywherelibrary.domain.entity.SimpleLibrary
import com.yourssu.anywherelibrary.domain.entity.UniversityRanking
import com.yourssu.anywherelibrary.domain.usecase.GetLibrariesUsecase
import com.yourssu.anywherelibrary.util.SingleLiveEvent
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class LibraryListViewModel(
    private val getLibrariesUsecase: GetLibrariesUsecase = GetLibrariesUsecase()
) : ViewModel() {
    private val _currentPage: MutableLiveData<Int> = MutableLiveData()
    val currentPage: LiveData<Int>
        get() = _currentPage

    private val _size: MutableLiveData<Int> = MutableLiveData()
    val size: LiveData<Int>
        get() = _size

    var libraries = ArrayList<SimpleLibrary>()
    var rakingList = ArrayList<UniversityRanking>()
    val getListCall : SingleLiveEvent<Void> = SingleLiveEvent()
    val getRankingListCall : SingleLiveEvent<Void> = SingleLiveEvent()

    init {
        _currentPage.value = 0
        _size.value = 12
    }

    fun getLibraryList() {
        getLibrariesUsecase.getLibraries(currentPage.value!!, size.value!!)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                libraries = it.libraries
                libraries.add(SimpleLibrary("", 0, "도서관 추가", null, null, 0))
                getListCall.call()
            }, {
                Log.d("MyTag", it.localizedMessage)
            })
    }

    fun getRankingList() {
        getLibrariesUsecase.getRankingList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                rakingList = it.ranks
                getRankingListCall.call()
            }, {
                Log.d("MyTag", it.localizedMessage)
            })
    }
}