package com.faysalh.eproducts.adapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.faysalh.eproducts.databinding.ItemCategoryBinding
import com.faysalh.eproducts.model.cateitem.Category
import com.faysalh.eproducts.utils.CATEGORIES_IMAGE_URL

class CategoryAdapter(private val context: Context):RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {
    var catlist: List<Category>?=null
    fun setListAdapter(lists: List<Category>){
        this.catlist=lists
    }
    class CategoryViewHolder(val binding:ItemCategoryBinding) :ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val binding = ItemCategoryBinding.inflate(LayoutInflater.from(context),parent,false)
        return CategoryViewHolder(binding)
    }
    override fun getItemCount(): Int {
        if (catlist == null)return 0
        else return catlist!!.size
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val data = catlist?.get(position)
        if (data != null) {
            holder.binding.itemLabel.text=data.name
            Glide.with(context).load(CATEGORIES_IMAGE_URL+data.icon).into(holder.binding.itemImage)
            holder.binding.itemImage.setBackgroundColor(Color.parseColor(data.color))
        }



    }


}