package com.yeditepe.acm43project.week9.network
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface FakeStoreAPIService {

    @GET("/products")
    fun getProducts(): List<Product>

    @GET("/products/{sid}")
    fun getProductById(@Path("sid") sid: Int): Product

    @POST("/products")
    fun addProduct(@Body product: Product): Product

    @PUT("/products/{sid}")
    fun updateProduct(@Path("sid") sid: Int, @Body product: Product):Product

    @DELETE("/products/{sid}")
    fun deleteProduct(@Path("sid") sid: Int): Unit
}


