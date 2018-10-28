package com.herokuapp.newtechserver2.data

import org.springframework.data.mongodb.core.mapping.Document
import javax.persistence.Id

@Document(collection="productuploads")
data class Products (
        @Id
        val id: String,
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

data class ProductDescription (
        val image: String,
        var data: Products? = null,
        var imagearray: String?
)
