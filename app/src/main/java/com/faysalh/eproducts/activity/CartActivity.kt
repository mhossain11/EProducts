package com.faysalh.eproducts.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.faysalh.eproducts.adapter.CartAdapter
import com.faysalh.eproducts.databinding.ActivityCartBinding
import com.faysalh.eproducts.db.ProductDatabase
import com.faysalh.eproducts.model.productitem.Product
import com.faysalh.eproducts.utils.CartListener
import com.faysalh.eproducts.viewmodel.ProductDetailsFactory
import com.faysalh.eproducts.viewmodel.ProductDetailsViewModel
import com.google.android.material.snackbar.Snackbar

class CartActivity : AppCompatActivity(){
    lateinit var binding:ActivityCartBinding
    lateinit var viewModel: ProductDetailsViewModel
    lateinit var cartAdapter: CartAdapter
    private lateinit var productDatabase: ProductDatabase
    var totalTk =0.0

     var list :ArrayList<Product> =ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCartBinding.inflate(layoutInflater)
        setContentView(binding.root)
        productDatabase =ProductDatabase.getInstance(this)
        val providerFactory =ProductDetailsFactory(productDatabase)
        viewModel= ViewModelProvider(this@CartActivity,providerFactory)[ProductDetailsViewModel::class.java]

        val actionbar = supportActionBar
        actionbar?.setDisplayHomeAsUpEnabled(true)

        productRcl()
        observeAddProduct()

        val itemTouchHelper = object : ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN,
            ItemTouchHelper.RIGHT or ItemTouchHelper.LEFT
        ){
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
               val position = viewHolder.absoluteAdapterPosition
                viewModel.deleteProduct(cartAdapter.differ.currentList[position])
                list.remove(cartAdapter.differ.currentList[position])
                updatePrice()

                Snackbar.make(binding.root,"Meal deleted", Snackbar.LENGTH_LONG).setAction(
                    "Undo",
                     View.OnClickListener {
                         viewModel.insertProduct(cartAdapter.differ.currentList[position])

                    }
                ).show()
            }
        }
ItemTouchHelper(itemTouchHelper).attachToRecyclerView(binding.rcl)

        binding.conbtn.setOnClickListener {
            val intent = Intent(this,CheckOutActivity::class.java)
            intent.putExtra("TOTAL",totalTk)
            startActivity(intent)
        }


    }


    private fun productRcl(){
        cartAdapter=CartAdapter(this,viewModel)
        binding.rcl.layoutManager =LinearLayoutManager(this)
        binding.rcl.adapter=cartAdapter
    }


    private fun observeAddProduct(){
        viewModel.observeProductAddLivedata().observe(this){
            list.clear()
            for (e in it) {
                list.add(e)
                Log.e("ERROR_13", "${e} ", )
            }
            cartAdapter.differ.submitList(list)
            cartAdapter.notifyDataSetChanged()
            updatePrice()
        }

        binding.rcl.adapter=cartAdapter

    }
    fun updatePrice(){
        var totalSum =0.0
        for (i in 0 until list.size){
            totalSum+= list[i].price.toString().toDouble()*list[i].quantity.toString().toInt()
        }
        totalTk=totalSum
        binding.subttl.text = String.format("Tk %.2f",totalSum)
    }




    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }  

    /*override fun onQuantityChanged(product: Product) {
        Log.e("ERROR_114", "$product ", )
          //  viewModel.insertProduct(product)

    }*/
}
