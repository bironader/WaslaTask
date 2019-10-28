package com.example.waslatask.features.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.blankj.utilcode.util.NetworkUtils
import com.example.waslatask.data.remote.QueriesApi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject


class MainViewModel @Inject constructor() : ViewModel() {

    lateinit var queriesApi: QueriesApi
    private var disposable: CompositeDisposable? = null
    private val suggestedItems = MutableLiveData<List<String>>()
    private val isConnected = MutableLiveData<Boolean>()

    init {
        disposable = CompositeDisposable()
        isConnected.value = true
    }

    fun makeRequest(queryToSearch: String) {
        if (isConnected()) {
            disposable?.add(
                queriesApi.getQuires(query = queryToSearch)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(this::onSuccess, this::onError)
            )
            isConnected.value = true
        } else
            isConnected.value = false
    }

    @Suppress("UNCHECKED_CAST")
    private fun onSuccess(response: List<Any>) {
        suggestedItems.value = response[1] as List<String>
    }

    private fun onError(throwable: Throwable) {

        Timber.d(throwable)
    }

    fun getsSuggestedItems(): LiveData<List<String>> = this.suggestedItems
    fun getConnectionStatus(): LiveData<Boolean> = this.isConnected

    private fun isConnected(): Boolean {
        return NetworkUtils.isConnected()
    }

}