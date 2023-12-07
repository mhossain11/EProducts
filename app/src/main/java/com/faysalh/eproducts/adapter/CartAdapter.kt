package com.faysalh.eproducts.adapter

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.faysalh.eproducts.databinding.ItemCartBinding
import com.faysalh.eproducts.databinding.QuantityDialogBinding
import com.faysalh.eproducts.model.productitem.Product
import com.faysalh.eproducts.viewmodel.ProductDetailsViewModel


class CartAdapter(val context: Context,val viewModel: ProductDetailsViewModel):RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

    //video: 3:02:31
    /*var catlist: List<Product>?=null
    fun setListAdapter(lists: List<Product>){
        this.catlist=lists
    }*/

   inner class CartViewHolder(val binding: ItemCartBinding):ViewHolder(binding.root){

   }

    private val diffUtil = object : DiffUtil.ItemCallback<Product>(){
        override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
          return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem == newItem
        }

    }


    val differ = AsyncListDiffer(this,diffUtil)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):CartViewHolder {
        val binding = ItemCartBinding.inflate(LayoutInflater.from(context),parent,false)
        return CartViewHolder(binding)
    }

    override fun onBindViewHolder(holder:CartViewHolder, position: Int) {
        val product = differ.currentList[position]
      //  val product = catlist?.get(position)
        var quntityMN = product.quantity // product  quantity

        holder.binding.nametxt.text = product?.name
        holder.binding.pricetxt.text = "TK "+ product?.price.toString()
        Glide.with(context).load(product?.image).into(holder.binding.productimage)
        holder.binding.quantitytxt.text = quntityMN.toString()

        holder.binding.itemView.setOnClickListener {
            val quantityBinding = QuantityDialogBinding.inflate(LayoutInflater.from(context))
            val dialog = AlertDialog.Builder(context)
                .setView(quantityBinding.root)
                .create()
         //  dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

           //quantityBinding.quantityresulttxt.text=quntityMN.toString()
            quantityBinding.productname.text=product?.name
            quantityBinding.stocktxt.text="Stock: "+product?.stock.toString()
            quantityBinding.quantityresulttxt.text=quntityMN.toString()
            val stock = product.stock

            quantityBinding.incrBtn.setOnClickListener {

                    quntityMN = quntityMN!! + 1

                if (stock != null) {
                    if(quntityMN!! > stock.toInt()){
                        Toast.makeText(
                            context,
                            "Max stock available: ${product.stock}",
                            Toast.LENGTH_SHORT
                        ).show()
                        return@setOnClickListener
                    }else{

                        product.quantity =quntityMN
                        notifyDataSetChanged()
                        quantityBinding.quantityresulttxt.text=quntityMN.toString()
                    }
                }

            }

            quantityBinding.decrBtn.setOnClickListener {
                if (quntityMN!! >1){
                    quntityMN = quntityMN!!-1
                    product.quantity=quntityMN
                    notifyDataSetChanged()
                    quantityBinding.quantityresulttxt.text=quntityMN.toString()

                }

            }

            quantityBinding.savebtn.setOnClickListener {

               // cartListener.onQuantityChanged(product)
                viewModel.insertProduct(product)
                notifyDataSetChanged()
                dialog.cancel()

            }
            dialog.show()


        }

    }

    override fun getItemCount(): Int {
        return differ.currentList.size
//        if (catlist == null)return 0
//        else return catlist!!.size
    }
}

