package com.user.view.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.user.indexedrecyclerview.RecyclerViewDataModel
import com.user.repositories.RecyclerViewDataRepository
import com.user.repositories.RecyclerViewDataRepository.Companion.instance

class RecyclerViewDataViewModel(application: Application) :
    AndroidViewModel(application) {
    private var mRecyclerViewDataModel: MutableLiveData<List<RecyclerViewDataModel>>? =
        null
    private var mRepo: RecyclerViewDataRepository? = null
    fun init() {
        if (mRecyclerViewDataModel != null) {
            return
        }
        mRepo = instance
        mRepo!!.setContext(getApplication())
        mRecyclerViewDataModel = mRepo!!.recyclerViewDataFromAPI
    }

    val recyclerViewData: LiveData<List<RecyclerViewDataModel>>?
        get() = mRecyclerViewDataModel
}