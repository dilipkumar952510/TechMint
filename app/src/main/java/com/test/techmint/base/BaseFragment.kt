package com.test.techmint.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.test.techmint.retrofit.RetrofitClient
import com.test.techmint.utils.PreEntity
import com.test.techmint.utils.Preferences

abstract class BaseFragment<VM : ViewModel , B : ViewBinding, R : BaseRepository> : Fragment(){

    protected lateinit var binding : B
    protected lateinit var viewModel : VM
    protected val retrofitClient = RetrofitClient()
    protected lateinit var authToken : String
    protected lateinit var deviceToken : String
    protected lateinit var currentUserId : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val factory = ViewModelFactory(getFragmentRepository())
        viewModel = ViewModelProvider(this,factory)[getViewModel()]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = getFragmentBinding(inflater,container)
        return binding.root
    }

    abstract fun getViewModel() : Class<VM>

    abstract fun getFragmentBinding(inflater: LayoutInflater,container: ViewGroup?) : B

    abstract fun getFragmentRepository() : R

    override fun onStart() {
        super.onStart()
//        getPaddingAccordingToStatusBarHeight()
    }

   /* private fun getPaddingAccordingToStatusBarHeight(){
        val statusBarSize: Int = getStatusBarHeight(requireContext())
        var paddingTop = 0

        if (statusBarSize > 150) {
            paddingTop = 150
        } else if (statusBarSize in 1..149) {
            paddingTop = 70
        }
        setPaddingAccordingToStatusBarHeight(paddingTop)
    }*/

//    abstract fun setPaddingAccordingToStatusBarHeight(paddingTop : Int)

    companion object{
        const val TAG = "BaseFragment"
    }
}