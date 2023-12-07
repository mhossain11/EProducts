package com.faysalh.eproducts.utils

import com.faysalh.eproducts.model.productitem.Product

interface CartListener {
    fun onQuantityChanged(product: Product)
}