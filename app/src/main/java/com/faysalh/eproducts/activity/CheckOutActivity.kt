package com.faysalh.eproducts.activity

import android.R
import android.app.ProgressDialog
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.faysalh.eproducts.adapter.CartAdapter
import com.faysalh.eproducts.databinding.ActivityCheckOutBinding
import com.faysalh.eproducts.db.ProductDatabase
import com.faysalh.eproducts.model.productitem.Product
import com.faysalh.eproducts.model.productorder.OrderModel
import com.faysalh.eproducts.model.productorder.ProductOrder
import com.faysalh.eproducts.model.productorder.ProductOrderDetail
import com.faysalh.eproducts.viewmodel.ProductDetailsFactory
import com.faysalh.eproducts.viewmodel.ProductDetailsViewModel
import com.faysalh.eproducts.viewmodel.ProductOrderViewModel
import java.util.Calendar


class CheckOutActivity : AppCompatActivity() {
    lateinit var binding:ActivityCheckOutBinding
    lateinit var viewModel: ProductDetailsViewModel
    lateinit var orderViewModel: ProductOrderViewModel
    private lateinit var productDatabase: ProductDatabase
    lateinit var cartAdapter: CartAdapter
    lateinit var progressDialog: ProgressDialog
    private var totalPrice=0.0
    var tax = 11
    var list :ArrayList<Product> =ArrayList()
    var id:String="0"
    lateinit var item:Product



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCheckOutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        progressDialog = ProgressDialog(this)
        progressDialog.setCancelable(false)
        progressDialog.setMessage("Loading..")

        val total = intent.getDoubleExtra("TOTAL",0.0)
        productDatabase = ProductDatabase.getInstance(this)
        val providerFactory = ProductDetailsFactory(productDatabase)
        viewModel= ViewModelProvider(this,providerFactory)[ProductDetailsViewModel::class.java]
       //   = AlertDialog
        val actionbar = supportActionBar
        actionbar?.setDisplayHomeAsUpEnabled(true)
        productRcl()
        observeAddProduct()
        binding.totalSum.text=String.format("Tk %.2f",total)

        totalPrice = (total * tax/100)+ total
        binding.total.text=totalPrice.toString()

        binding.checkoutBtn.setOnClickListener {
            processOrder()
            Log.e("Error",processOrder().toString())
        }

    }
    fun processOrder(){
        progressDialog.show()
       // id = intent.getStringExtra("Product_id").toString()
        val name = binding.fullNameTxt.text.toString()
        val email = binding.emailBoxTxt.text.toString()
        val phone = binding.phoneBoxTxt.text.toString()
        val address = binding.addressBoxTxt.text.toString()
        val date = Calendar.getInstance().timeInMillis
        val lastUpdate = Calendar.getInstance().timeInMillis
        val createdTime = Calendar.getInstance().timeInMillis
        val comment = binding.commentBoxTxt.text.toString()
        orderViewModel= ViewModelProvider(this)[ProductOrderViewModel::class.java]

            val productOrder = ProductOrder(address =address, buyer = name, comment = comment, email = email, phone = phone, dateShip = date, serial = "cab8c1a4e4421a3b",
                shipping = "", shippingLocation = "", shippingRate = "0.0", status = "WAITTING", tax = tax, totalFees = totalPrice, updatedAt = lastUpdate, createdAt = createdTime)
            val productOrderDetail = ProductOrderDetail(amount = item.quantity!!, priceItem =item.price!!.toInt() , createdAt = createdTime , productId =item.id.toInt(), productName =name , updatedAt = lastUpdate )

        val orderModel = OrderModel(productOrder = productOrder, productOrderDetail = productOrderDetail)

        Log.e("Error",productOrder.toString())
        Log.e("Error_002",productOrderDetail.toString())

        orderViewModel.getProduct(orderModel)
        orderViewModel.getProductOrderLiveDataObserve().observe(this){
            progressDialog.dismiss()
            Log.e("Error_01",it.toString())
        }
    //3:28:28


    }

    private fun productRcl(){
        cartAdapter=CartAdapter(this@CheckOutActivity, viewModel)
        binding.cartDetailsRcl.layoutManager = LinearLayoutManager(this)
        binding.cartDetailsRcl.adapter=cartAdapter


    }


    private fun observeAddProduct(){
        viewModel.observeProductAddLivedata().observe(this){
            list.clear()
            for (e in it) {
                list.add(e)
                Log.e("ERROR_13", "${e} ")
                item = e
            }
            cartAdapter.differ.submitList(list)
            cartAdapter.notifyDataSetChanged()

        }

        binding.cartDetailsRcl.adapter=cartAdapter

    }
    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }

}