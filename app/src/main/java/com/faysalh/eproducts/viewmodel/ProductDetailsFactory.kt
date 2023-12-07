package com.faysalh.eproducts.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.faysalh.eproducts.db.ProductDatabase

class ProductDetailsFactory(val productDatabase: ProductDatabase) :ViewModelProvider.Factory{

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ProductDetailsViewModel(productDatabase) as T
    }
}