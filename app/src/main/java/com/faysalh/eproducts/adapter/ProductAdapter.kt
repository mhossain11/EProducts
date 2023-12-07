package com.faysalh.eproducts.adapter

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.faysalh.eproducts.activity.ProductDetailActivity
import com.faysalh.eproducts.databinding.ItemProductBinding
import com.faysalh.eproducts.model.productitem.Product
import com.faysalh.eproducts.utils.PRODUCTS_IMAGE_URL

class ProductAdapter(val context: Context): RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {
    var productList:List<Product>?=null

    fun setProductAdapter(lists:List<Product>){
        this.productList = lists
    }

    class ProductViewHolder(val binding: ItemProductBinding):ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding = ItemProductBinding.inflate(LayoutInflater.from(context),parent,false)
        return ProductViewHolder(binding)
    }
    override fun getItemCount(): Int {
        if (productList == null) return 0
        else return productList!!.size
    }


    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val data = productList?.get(position)
        val img = PRODUCTS_IMAGE_URL+data?.image
        if (data != null) {
            Glide.with(context).load(img).into(holder.binding.imageProduct)
            holder.binding.labelProduct.text=data.name
            holder.binding.priceProduct.text=data.price.toString()+"TK"
        }
        holder.binding.fullProduct.setOnClickListener {
            val intent = Intent(context,ProductDetailActivity::class.java)

            intent.putExtra("Product_name",data?.name)
            intent.putExtra("Product_image",img)
            intent.putExtra("Product_id",data?.id.toString())
            intent.putExtra("Product_price",data?.price)
            intent.putExtra("Product_stock",data?.stock)
          //  Log.e("DATA_SHOW",  data?.id.toString() )

            context.startActivity(intent)
        }

    }


}