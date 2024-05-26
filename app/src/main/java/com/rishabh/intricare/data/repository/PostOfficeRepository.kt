package com.rishabh.intricare.data.repository

import androidx.lifecycle.LiveData
import com.rishabh.intricare.data.api.ApiService
import com.rishabh.intricare.data.api.RetrofitInstance
import com.rishabh.intricare.data.model.PostOffice
import com.rishabh.intricare.data.model.PostOfficeResponse
import com.rishabh.intricare.data.room.PostOfficeDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import retrofit2.Response

class PostOfficeRepository(
    private val postOfficeDao: PostOfficeDao,
    private val postOfficeApiService: ApiService) {

    val allPostOffices: LiveData<List<PostOffice>> = postOfficeDao.getAllPostOffices()

    suspend fun fetchPostOfficesFormApi(city: String) {
        withContext(Dispatchers.IO) {
            try {
                val response = postOfficeApiService.getPostOffices(city)
                if (response.isSuccessful) {
                    response.body()?.let {
                        postOfficeDao.insertAll(it.postOffices)
                    }
                } else {
                    // error response case
                    throw HttpException(response)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}