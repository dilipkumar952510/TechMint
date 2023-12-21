package com.test.techmint.fragment.webview

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.test.techmint.R
import com.test.techmint.databinding.FragmentWebViewBinding
import com.test.techmint.model.Item

class WebViewFragment : Fragment() {
    private lateinit var binding : FragmentWebViewBinding
    private lateinit var data : Item
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.bind(inflater.inflate(R.layout.fragment_web_view, container, false))!!
        setWebView()
        return binding.root
    }

    private fun setWebView() {
        data = requireArguments().getSerializable("item") as Item
        binding.heading.text = data.name

        binding.webView.loadUrl(data.owner.html_url!!)
    }

    inner class ClickAction{
        fun onBack(view: View){

        }
    }
}