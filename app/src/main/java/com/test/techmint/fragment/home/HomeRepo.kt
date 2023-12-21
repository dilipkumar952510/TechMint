package com.test.techmint.fragment.home

import com.test.techmint.base.BaseRepository
import com.test.techmint.retrofit.APIService

class HomeRepo(private val apiService: APIService):BaseRepository() {
    suspend fun homeData(search:String,page:Int) = safeApiCall {
        apiService.home(search,page)
    }
}