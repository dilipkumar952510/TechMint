package com.test.techmint.fragment.repoDetails

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.test.techmint.R
import com.test.techmint.databinding.FragmentRepoDetailsBinding
import com.test.techmint.fragment.webview.WebViewFragment
import com.test.techmint.model.Item

class RepoDetailsFragment : Fragment() {
    private lateinit var binding: FragmentRepoDetailsBinding
    private lateinit var data:Item
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.bind(inflater.inflate(R.layout.fragment_repo_details, container, false))!!
        binding.click = ClickAction()
        setData()
        return binding.root
    }

    private fun setData() {
        data = requireArguments().getSerializable("item") as Item
        binding.data = data
        Glide.with(requireActivity()).load(data.owner.avatar_url).placeholder(R.color.grey).into(binding.imageView)
    }

    inner class ClickAction{
        fun onBack(view: View){
            findNavController().navigateUp()
        }

        fun onLinkClick(view: View){
            val bundle = Bundle()
            bundle.putSerializable("item",data)
            WebViewFragment().arguments  = bundle
            findNavController().navigate(R.id.action_repoFragment_to_webViewFragment,bundle)
        }
    }
}