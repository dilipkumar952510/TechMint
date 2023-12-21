package com.test.techmint.retrofit

import com.test.techmint.model.HomeResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface APIService {
    @GET("search/repositories")
    suspend fun home(@Query("q")q:String,@Query("page")page:Int) : HomeResponse
}