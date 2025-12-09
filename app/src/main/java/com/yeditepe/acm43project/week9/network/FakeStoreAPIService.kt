package com.yeditepe.acm43project.week9.network
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface FakeStoreAPIService {

    @GET("/products")
    suspend fun getProducts(): Response<List<Product>>

    @GET("/products/{sid}")
    suspend fun getProductById(@Path("sid") sid: Int): Product

    @POST("/products")
    suspend fun addProduct(@Body product: Product): Product

    @PUT("/products/{sid}")
    suspend fun updateProduct(@Path("sid") sid: Int, @Body product: Product):Product

    @DELETE("/products/{sid}")
    suspend fun deleteProduct(@Path("sid") sid: Int): Unit
}


