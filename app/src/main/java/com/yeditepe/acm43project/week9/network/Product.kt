package com.yeditepe.acm43project.week9.network

data class Product(
    val id:Int,
    val title:String,
    val price:Double,
    val description:String,
    val image:String,
    val rating:Rating
)

data class Rating(
    val rate:Double,
    val count:Int
)

