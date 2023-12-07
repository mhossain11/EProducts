package com.faysalh.eproducts.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.faysalh.eproducts.adapter.CategoryAdapter
import com.faysalh.eproducts.adapter.ProductAdapter
import com.faysalh.eproducts.databinding.ActivityMainBinding
import com.faysalh.eproducts.utils.NEWS_IMAGE_URL
import com.faysalh.eproducts.viewmodel.CategoryViewModel
import com.faysalh.eproducts.viewmodel.NewsInfoViewModel
import com.faysalh.eproducts.viewmodel.ProductViewModel
import org.imaginativeworld.whynotimagecarousel.model.CarouselItem


class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var categoryViewModel :CategoryViewModel
    lateinit var adaptercate: CategoryAdapter
    lateinit var   productViewModel :ProductViewModel
    lateinit var adapterproduct: ProductAdapter
    lateinit var newsInfoViewModel: NewsInfoViewModel
  //  val list = ArrayList<CategoryModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


      categoriesInitViewModel()
        categories()
      productsInitViewModel()
        products()
        newsInfoSlider()

    }

    //Slider
    private fun newsInfoSlider() {
       newsInfoViewModel = ViewModelProvider(this)[NewsInfoViewModel::class.java]
       newsInfoViewModel.getNewsInfoLiveDataObserve().observe(this){

           val data = it?.newsInfos
           if (data != null) {
               for (e in data) {
                   binding.carouselSlider.addData(CarouselItem(NEWS_IMAGE_URL +"${e.image}","${e.title}"))
               }

           }
       }
        newsInfoViewModel.getNewsInfo()

    }

    //product viewModel
   private fun  productsInitViewModel(){
       productViewModel = ViewModelProvider(this)[ProductViewModel::class.java]
       productViewModel.getProductLiveDataObserve().observe(this){
           Log.e("PRODUCTVIEW_Null", it.toString(), )
           if (it != null) {
               Log.e("PRODUCTVIEW_NOT_Null", it.toString(), )
               adapterproduct.setProductAdapter(it.products)
               adaptercate.notifyDataSetChanged()
           }

       }
       productViewModel.getProduct()

   }
    //product recyclerview
    private fun products() {
      //  val list = ArrayList<Product>()
      //  list.add(Product("Korean Loose Short Cowboy Outerwear","https://www.tackroominc.com/images/Ariat%20Lovely%20Western%20Boots_10027229.jpg","",2400.0,12.0,3))

        binding.productRcl.layoutManager=GridLayoutManager(this,2)
        adapterproduct = ProductAdapter(this)
        binding.productRcl.adapter=adapterproduct
    }

    //categories viewModel
    private fun categoriesInitViewModel(){
        categoryViewModel = ViewModelProvider(this@MainActivity)[CategoryViewModel::class.java]
        categoryViewModel.getCategoryLiveDataObserve().observe(this){
            if (it != null) {
                Log.e("VIEW_NOT_Null", it.toString(), )
                adaptercate.setListAdapter(it.categories)
                adaptercate.notifyDataSetChanged()
            }


        }
        categoryViewModel.categoryAPI()
    }
    //categories recyclerview
    private fun categories() {
     //   list.add(CategoryModel("Sports & Outdoor","https://cdn-icons-png.flaticon.com/512/857/857681.png","#18ab4e","Some Description","","","",""))


        binding.CategoryRcl.layoutManager= LinearLayoutManager(this,
            LinearLayoutManager.HORIZONTAL,false)
        adaptercate = CategoryAdapter(this)
        binding.CategoryRcl.adapter=adaptercate
    }

}