package com.yeditepe.acm43project.week8.ui

import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost

import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.yeditepe.acm43project.R
import com.yeditepe.acm43project.ui.theme.ACM43ProjectTheme
import androidx.navigation.compose.composable
import com.yeditepe.acm43project.week8.viewmodel.Product
import com.yeditepe.acm43project.week8.viewmodel.ProductViewModel


@Composable
fun ProductListView(productModel: ProductViewModel,
                    controller: NavController){

    val state = productModel.uiState.collectAsState().value

    LazyColumn(modifier = Modifier.fillMaxSize()) {

        items(state){
            item->
            ProductCard(product = item, controller = controller)
        }

    }



}


@Composable
fun ProductCard(product: Product, controller: NavController) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable {
                controller.navigate("detail/${product.id}")
            },

        ) {
        Row(
            modifier = Modifier.padding(16.dp)
        ) {
            Image(
                painter = painterResource(product.image),
                contentDescription = product.title,
                modifier = Modifier.size(100.dp),
                contentScale = ContentScale.Fit
            )
            Column(
                modifier = Modifier
                    .padding(start = 16.dp)
                    .weight(1f)
            ) {
                Text(
                    text = product.title,
                    style = MaterialTheme.typography.titleMedium
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "$${product.price}",
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
    }
}

@Composable
fun ProductDetails(productId: String?, productModel: ProductViewModel) {
    val product = productModel.findProduct(productId?:"null")

    if (product != null) {
        Card(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = product.image),
                    contentDescription = product.title,
                    modifier = Modifier
                        .size(250.dp)
                        .padding(bottom = 16.dp),
                    contentScale = ContentScale.Fit
                )
                Text(
                    text = product.title,
                    style = MaterialTheme.typography.headlineMedium,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Text(
                    text = "$${product.price}",
                    style = MaterialTheme.typography.titleLarge
                )
            }
        }
    } else {
        Text(text = "Product not found")
    }
}

enum class Destination(@StringRes val route: Int) {
    List(R.string.dest1),
    Detail(R.string.dest2)
}

@Composable
fun MainView(productModel: ProductViewModel,
             controller: NavController){

    NavHost(navController = controller,
        startDestination = Destination.List.route.toString()){
            composable(route = Destination.List.route.toString()){
                ProductListView(productModel = productModel, controller = controller)
            }
            composable(
                route = Destination.Detail.name + "/{productId}",
                arguments = listOf(navArgument("productId"){
                    nullable = true
                })
            )
            {
                    backstack->
                ProductDetails(
                    productId = backstack.arguments?.getString("productId"),
                    productModel = productModel)
            }

        }
    }



@Preview
@Composable
fun MainScreenPrewiew(){
    val productModel: ProductViewModel = viewModel()
    val controller = rememberNavController()
    ACM43ProjectTheme {
        MainView(productModel = productModel, controller = controller)

    }
}
