package com.user.indexedrecyclerview

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class RecyclerViewDataModel(
    val title: String,
    val type: String? = null,
    val info_collected: String? = null,
    val info_used: String? = null
) : Parcelable