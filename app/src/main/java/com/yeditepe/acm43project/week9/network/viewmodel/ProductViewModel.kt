package com.yeditepe.acm43project.week9.network.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yeditepe.acm43project.week9.network.Product
import com.yeditepe.acm43project.week9.network.Client
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

enum class UIState{
    LOADING,
    SUCCESS,
    ERROR
}
class ProductViewModel: ViewModel() {
    var uiState = MutableStateFlow(UIState.LOADING)
    var _products = MutableStateFlow<List<Product>>(listOf())

    val products = _products.asStateFlow()
    init{
        getProduct()
    }

    fun getProduct() {
        viewModelScope.launch {
            val response = Client.service.getProducts()
            if (response.code() == 200) {
                _products.value = response.body()!!
            }
            uiState.value = UIState.SUCCESS
        }
    }
}