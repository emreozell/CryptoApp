package com.emreozel.cryptoapp.base

import com.emreozel.cryptoapp.model.errorResponse.ErrorResponse
import com.emreozel.cryptoapp.utils.NetworkResult
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException

abstract class BaseRepository {

    suspend fun <T> safeApiRequest(
        apiRequest:suspend ()->T):NetworkResult<T>{
        return withContext(Dispatchers.IO){
            try {
                NetworkResult.Success(apiRequest.invoke())

            }
            catch (throwbale:Throwable){
                when(throwbale){
                    is HttpException ->{
                    NetworkResult.Error(false, errorBodyParse(throwbale.response()?.errorBody()?.string()))
                }
                    else -> NetworkResult.Error(true,throwbale.localizedMessage)
                }

            }
        }
    }


}
private fun errorBodyParse(error:String?):String{
    error?.let{
        return try {
            val errorResponse= Gson().fromJson(error,ErrorResponse::class.java)
            val errorMessage=errorResponse.status?.errorMessage
            errorMessage ?:"Bilinmeyen bir hata oluştu"
        }catch (e:Exception){
            "Bilinmeyen bir hata oluştu"
        }
    }
    return "Bilinmeyen bir hata oluştu"
}