package com.test.techmint.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.test.techmint.fragment.home.HomeRepo
import com.test.techmint.fragment.home.HomeViewModel

@Suppress("UNCHECKED_CAST")

class ViewModelFactory(private val repository : BaseRepository) : ViewModelProvider.NewInstanceFactory(){
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when{
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> HomeViewModel(repository as HomeRepo) as T
            else -> throw IllegalArgumentException("view model class not found")
        }
    }
}