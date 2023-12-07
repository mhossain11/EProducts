
package com.faysalh.eproducts.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.HtmlCompat
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.faysalh.eproducts.R
import com.faysalh.eproducts.databinding.ActivityProductDetailBinding
import com.faysalh.eproducts.db.ProductDatabase
import com.faysalh.eproducts.model.productitem.Product
import com.faysalh.eproducts.viewmodel.ProductDetailsFactory
import com.faysalh.eproducts.viewmodel.ProductDetailsViewModel

class ProductDetailActivity : AppCompatActivity() {
    lateinit var binding: ActivityProductDetailBinding
    lateinit var viewModel:ProductDetailsViewModel
    lateinit var productDatabase: ProductDatabase
    lateinit var id: String
    private var price: String? = " "
    private var name: String = ""
    private var images:String =" "
    private var stock:String =" "
    private var quantity:Int?=null
    private lateinit var products: Product


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val actionbar = supportActionBar
        actionbar?.setDisplayHomeAsUpEnabled(true)


        //from product adapter
        name = intent.getStringExtra("Product_name").toString()
        id = intent.getStringExtra("Product_id").toString()
        price = intent.getStringExtra("Product_price")
        stock = intent.getStringExtra("Product_stock").toString()
        images = intent.getStringExtra("Product_image").toString()
        Glide.with(this).load(images).into(binding.imageView2)
        productDetailsInitViewModel()

        binding.cartBtn.text="Add to Cart"

      //  binding.cartBtn.setBackgroundColor()
        //button
        binding.cartBtn.setOnClickListener {
            quantity =1
            products=Product(id = id, name = name, image = images, price = price, stock = stock, quantity =quantity!! )
            productDatabase=ProductDatabase.getInstance(this) //First sql data pass
            viewModel.insertProduct(products)
            binding.cartBtn.isEnabled = false
            binding.cartBtn.text="Added in Cart"

            Log.e("PRODUCT_DATA", products.toString())

        }
     }

    private fun productDetailsInitViewModel() {
        val productDatabase =ProductDatabase.getInstance(this)
        val viewModelFactory = ProductDetailsFactory(productDatabase)
        viewModel = ViewModelProvider(this,viewModelFactory)[ProductDetailsViewModel::class.java]

        viewModel.getProductDetailsLiveDataObserve().observe(this){
            Log.e("ProductDetails_Not_Null", it.toString())
            if (it != null) {
                Log.e("ProductDetails_it", "OK")
                val html = it.product.description
                binding.descripProducttxt.text = HtmlCompat.fromHtml(html,HtmlCompat.FROM_HTML_MODE_LEGACY)

            }
        }

        id.let { viewModel.getProductDetails(it) }

    }
    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.carts_item, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.add_Btn ->{
                val intent = Intent(this@ProductDetailActivity,CartActivity::class.java )
                startActivity(intent)
                return true
            } else -> {
                super.onOptionsItemSelected(item)
            }

         }

    }


}

