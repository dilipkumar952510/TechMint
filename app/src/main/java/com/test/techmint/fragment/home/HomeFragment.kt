package com.test.techmint.fragment.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.test.techmint.R
import com.test.techmint.base.BaseFragment
import com.test.techmint.databinding.FragmentHomeBinding
import com.test.techmint.fragment.repoDetails.RepoDetailsFragment
import com.test.techmint.model.HomeResponse
import com.test.techmint.model.Item
import com.test.techmint.model.LastData
import com.test.techmint.retrofit.APIService
import com.test.techmint.retrofit.Resource
import com.test.techmint.utils.PreEntity
import com.test.techmint.utils.Preferences
import com.test.techmint.utils.ProcessDialog

class HomeFragment : BaseFragment<HomeViewModel,FragmentHomeBinding,HomeRepo>(),HomeAdapter.OnHomeSelected {
    private var isLastPage = false
    private var currentPage = 1
    private var type = NORMAL
    private lateinit var adapter : HomeAdapter
    private var list = ArrayList<Item>()
    private var search ="Android"
    override fun getViewModel(): Class<HomeViewModel> = HomeViewModel::class.java
    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentHomeBinding = FragmentHomeBinding.inflate(inflater,container,false)

    override fun getFragmentRepository(): HomeRepo = HomeRepo(retrofitClient.buildApi(APIService::class.java))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getHomeData()
    }

    private fun getHomeData() {
        viewModel.getHomeData(search,currentPage)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if(Preferences.getStringPreference(requireActivity(), PreEntity.LAST_SAVE_LIST)!=null)
            initAdapter()
        setTextWatcher()
        setObserver()
    }

    private fun initAdapter() {
        val last = Gson().fromJson(Preferences.getStringPreference(requireActivity(),PreEntity.LAST_SAVE_LIST),LastData::class.java)
        list.addAll(last.item)
        adapter = HomeAdapter(requireActivity(), list, this)
        binding.recyclerView.adapter = adapter
    }

    private fun setObserver() {
        binding.recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val layoutManager = binding.recyclerView.layoutManager as LinearLayoutManager
                if (layoutManager.findLastVisibleItemPosition() == adapter.itemCount - 3) {
                    if (!isLastPage) {
                        currentPage++
                        type = PAGING
                        getHomeData()
                    }
                }
            }
        })

        viewModel.homeResponse.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Loading -> {
                    ProcessDialog.startDialog(requireActivity())
                }
                is Resource.Failure -> {
                    ProcessDialog.dismissDialog()
                }
                is Resource.Success -> {
                    ProcessDialog.dismissDialog()
                    if (currentPage == 1)
                        list = ArrayList()
                    list.addAll(it.value.items)
                    isLastPage = it.value.items.size == 0

                    when (type) {
                        NORMAL -> {
                            adapter = HomeAdapter(requireActivity(), list, this)
                            binding.recyclerView.adapter = adapter
                        }
                        PAGING -> adapter.updateList(list)
                    }

                    if (it.value.items.size>15) {
                        val newList = ArrayList<Item>()
                        for (i in list.size - 1 downTo 15) {
                            newList.add(list[i])
                        }

                        val last = LastData("last",newList)
                        Preferences.setStringPreference(requireActivity(),PreEntity.LAST_SAVE_LIST,Gson().toJson(last))
                    }
                }
            }
        }
    }


    private fun setTextWatcher() {
        binding.seach.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                currentPage = 1
                adapter.clearPosts()
                getHomeData()
            }
            true
        }

        binding.seach.doAfterTextChanged {
            if (it.toString().isEmpty())
                search = "Android"
            else
                search = it.toString()
        }
    }

    companion object {
        private const val NORMAL = "normal"
        private const val PAGING = "paging"
    }

    override fun onSelected(data: Item) {
        val bundle = Bundle()
        bundle.putSerializable("item",data)
        RepoDetailsFragment().arguments = bundle
        findNavController().navigate(R.id.action_homeFragment_to_repoFragment,bundle)
    }
}