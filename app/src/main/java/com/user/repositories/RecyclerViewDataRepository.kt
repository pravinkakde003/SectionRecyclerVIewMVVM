package com.user.repositories

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.user.indexedrecyclerview.R
import com.user.indexedrecyclerview.RecyclerViewDataModel
import com.user.indexedrecyclerview.loadJsonData
import java.util.*

class RecyclerViewDataRepository {
    private var recyclerViewDataList: List<RecyclerViewDataModel> = ArrayList()
    private var mContext: Context? = null

    fun setContext(context: Context?) {
        mContext = context
    }

    // Pretend to get data from a webservice or online database somewhere
    val recyclerViewDataFromAPI: MutableLiveData<List<RecyclerViewDataModel>>
        get() {
            setDummyData()
            val data = MutableLiveData<List<RecyclerViewDataModel>>()
            data.value = recyclerViewDataList
            return data
        }

    private fun setDummyData() {
        recyclerViewDataList = mContext!!.loadJsonData(R.raw.dummy_data)
        recyclerViewDataList = recyclerViewDataList.sortedBy { it.title }
    }

    companion object {
        @JvmStatic
        var instance: RecyclerViewDataRepository? = null
            get() {
                if (field == null) {
                    field = RecyclerViewDataRepository()
                }
                return field
            }
    }
}