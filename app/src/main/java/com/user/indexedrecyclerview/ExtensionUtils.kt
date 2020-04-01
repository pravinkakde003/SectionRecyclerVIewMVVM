package com.user.indexedrecyclerview

import android.content.Context
import android.widget.Toast
import androidx.annotation.RawRes
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


inline fun <reified T> Context.loadJsonData(@RawRes jsonFileId: Int): T {
    val inputStream = this.resources.openRawResource(R.raw.dummy_data)
    val size = inputStream.available()
    val buffer = ByteArray(size)
    inputStream.read(buffer)
    inputStream.close()
    val json = String(buffer)
    return Gson().fromJson(json, object : TypeToken<T>() {}.type)
}


inline fun Context.toast(message: () -> CharSequence) {
    Toast.makeText(this, message(), Toast.LENGTH_SHORT).show()
}