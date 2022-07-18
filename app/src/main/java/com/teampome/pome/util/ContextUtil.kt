package com.teampome.pome.util

import android.content.Context
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber

fun Context.showToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun <ResponseType> Call<ResponseType>.enqueueUtil(
    onSuccess: (ResponseType) -> Unit,
    onError: ((stateCode: Int) -> Unit)? = null
) {
    this.enqueue(object : Callback<ResponseType> {
        override fun onResponse(call: Call<ResponseType>, response: Response<ResponseType>) {
            if (response.isSuccessful) {
                onSuccess.invoke(response.body() ?: return)
            } else {
                onError?.invoke(response.code())
            }
        }

        override fun onFailure(call: Call<ResponseType>, t: Throwable) {
            Timber.d("NetworkTest", "error:$t")
        }

    }
    )
}