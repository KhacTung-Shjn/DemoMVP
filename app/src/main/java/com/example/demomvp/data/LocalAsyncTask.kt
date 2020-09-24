package com.example.demomvp.data

import android.os.AsyncTask

class LocalAsyncTask<P, T>(
    private val callback: OnDataLoadedCallback<T>,
    private val handler: (P) -> T
) : AsyncTask<P, Void, T>() {

    private var exception: Exception? = null

    override fun doInBackground(vararg params: P): T? {
        try {
            handler(params.first()) ?: throw Exception()
        } catch (e: Exception) {
            exception = e
        }
        return null
    }

    override fun onPostExecute(result: T) {
        result?.let(callback::onSuccess) ?: (callback::onFailure)
    }

}
