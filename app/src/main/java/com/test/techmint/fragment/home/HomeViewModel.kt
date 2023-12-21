package com.test.techmint.fragment.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.techmint.model.HomeResponse
import com.test.techmint.retrofit.Resource
import kotlinx.coroutines.launch

class HomeViewModel(val repo: HomeRepo):ViewModel() {
    private val homeLiveData : MutableLiveData<Resource<HomeResponse>> = MutableLiveData()
    val homeResponse : LiveData<Resource<HomeResponse>>
        get() = homeLiveData

    fun getHomeData(search:String,page:Int) = viewModelScope.launch {
        homeLiveData.value = Resource.Loading
        homeLiveData.value = repo.homeData(search,page)
    }
}