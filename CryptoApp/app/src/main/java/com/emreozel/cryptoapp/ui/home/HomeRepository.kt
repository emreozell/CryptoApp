package com.emreozel.cryptoapp.ui.home


import com.emreozel.cryptoapp.base.BaseRepository
import com.emreozel.cryptoapp.network.ApiFactory
import javax.inject.Inject

class HomeRepository @Inject constructor(private val apiFactory: ApiFactory):BaseRepository() {

    suspend fun getData(
        apiKey:String,
        limit:String
    )=safeApiRequest {
        apiFactory.getData(apiKey,limit)
    }
}