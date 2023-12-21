package com.test.techmint.fragment.home

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.test.techmint.R
import com.test.techmint.base.BaseFragment
import com.test.techmint.databinding.HomeLayoutBinding
import com.test.techmint.model.Item

class HomeAdapter(val context:Context, var list : ArrayList<Item>, val onHomeSelected: OnHomeSelected):RecyclerView.Adapter<HomeAdapter.ViewHolder>() {
    class ViewHolder(val binding:HomeLayoutBinding):RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.home_layout,parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.data = list[position]
        Glide.with(context).load(list[position].owner.avatar_url).placeholder(R.color.grey).into(holder.binding.imageView)
        holder.binding.mainLayout.setOnClickListener {
            onHomeSelected.onSelected(list[position])
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun updateList(list: java.util.ArrayList<Item>) {
        this.list = list
        notifyDataSetChanged()
    }

    fun clearPosts() {
        list = ArrayList()
        notifyDataSetChanged()
    }

    interface OnHomeSelected{
        fun onSelected(data:Item)
    }
}