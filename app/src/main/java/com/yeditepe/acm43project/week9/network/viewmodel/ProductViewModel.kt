package com.yeditepe.acm43project.week9.network.viewmodel

import androidx.lifecycle.ViewModel
import com.yeditepe.acm43project.week8.viewmodel.Product
import com.yeditepe.acm43project.week9.network.Client
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class ProductViewModel: ViewModel() {

    var _products: MutableStateFlow<List<Product>> = MutableStateFlow(List<Product>())
    init{
        getProduct()
    }
    val products = _products.asStateFlow()


    fun getProduct(){
        _products.value = Client.service.getProducts()
    }


}