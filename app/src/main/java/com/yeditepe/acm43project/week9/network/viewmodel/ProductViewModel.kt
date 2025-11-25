package com.yeditepe.acm43project.week9.network.viewmodel

import androidx.lifecycle.ViewModel
import com.yeditepe.acm43project.week9.network.Product
import com.yeditepe.acm43project.week9.network.Client
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class ProductViewModel: ViewModel() {

    var _products = MutableStateFlow<List<Product>>(listOf())

    val products = _products.asStateFlow()
    init{
        getProduct()
    }

    fun getProduct(){
        _products.value = Client.service.getProducts()
    }


}