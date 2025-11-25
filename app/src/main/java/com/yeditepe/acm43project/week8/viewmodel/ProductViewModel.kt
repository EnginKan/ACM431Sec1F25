package com.yeditepe.acm43project.week8.viewmodel

import com.yeditepe.acm43project.R
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

data class Product(
    val id: String,
    var title: String,
    var description: String,
    var price: Double,
    var image: Int
)

var example= listOf(
    Product("1", "Product 1", "Description 1", 10.0, R.drawable.ic_launcher_background),
    Product("2", "Product 2", "Description 2", 20.0, R.drawable.ic_launcher_background),
    Product("3", "Product 3", "Description 3", 30.0, R.drawable.ic_launcher_background),
    Product("4", "Product 4", "Description 4", 40.0, R.drawable.ic_launcher_background),
    Product("5", "Product 5", "Description 5", 50.0, R.drawable.ic_launcher_background),
    Product("6", "Product 6", "Description 6", 60.0, R.drawable.ic_launcher_background),
    Product("7", "Product 7", "Description 7", 70.0, R.drawable.ic_launcher_background),
    Product("8", "Product 8", "Description 8", 80.0, R.drawable.ic_launcher_background),
    Product("9", "Product 9", "Description 9", 90.0, R.drawable.ic_launcher_background),
    Product("10", "Product 10", "Description 10", 100.0, R.drawable.ic_launcher_background),
)
class ProductViewModel(products: List<Product> =example ): ViewModel()  {
    //not visible
    var _uiState = MutableStateFlow<List<Product>>(products)
    //visible to outside word
    val uiState = _uiState.asStateFlow()

    fun findProduct(id: String): Product? {
        if(!id.isNullOrBlank())
         return _uiState.value.find { it.id == id }
        else
            return null
    }



}