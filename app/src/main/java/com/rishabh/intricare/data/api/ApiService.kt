package com.rishabh.intricare.data.api

import com.rishabh.intricare.data.model.PostOfficeResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("postoffice/{city}")
    suspend fun getPostOffices(
        @Path("city")
        city : String
    ) : Response<PostOfficeResponse>
}