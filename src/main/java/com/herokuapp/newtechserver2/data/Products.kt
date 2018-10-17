package com.herokuapp.newtechserver2.data

import org.springframework.boot.configurationprocessor.json.JSONObject
import org.springframework.data.mongodb.core.mapping.Document
import java.util.*
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

data class ProductList(
        val motherboard: String
//        val processor: ArrayList<String>,
//        val graphiccard: ArrayList<String>,
//        val monitor: ArrayList<String>,
//        val router: ArrayList<String>
)