package com.yourssu.anywherelibrary.presentation.library_list

import android.util.Log
import androidx.lifecycle.ViewModel
import com.yourssu.anywherelibrary.domain.usecase.PostLibraryUsecase
import com.yourssu.anywherelibrary.util.SingleLiveEvent
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class LibraryPostViewModel(
    private val postLibraryUsecase: PostLibraryUsecase = PostLibraryUsecase()
): ViewModel() {
    val postSuccess : SingleLiveEvent<Void> = SingleLiveEvent()

    fun postLibrary(hangout : String, name: String, totalPersonnel: Int) {
        postLibraryUsecase.postLibrary(hangout, name, totalPersonnel)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                postSuccess.call()
            }, {
                Log.d("MyTag", it.localizedMessage)
            })
    }
}