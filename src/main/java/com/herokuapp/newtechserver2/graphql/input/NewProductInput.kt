package com.herokuapp.newtechserver2.graphql.input

data class NewProductInput(
        val title: String,
        val brand: String,
        val category: String,
        val modalno: String,
        val price: String,
        val image: String,
        val shortdescription: String,
        val fulldescription: String,
        val productimages: String,
        val arrivaldate: String
)