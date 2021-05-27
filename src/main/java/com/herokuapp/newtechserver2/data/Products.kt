package com.herokuapp.newtechserver2.data

import org.springframework.data.mongodb.core.mapping.Document
import javax.persistence.Id

@Document(collection="productuploads")
data class Products (
        @Id
        val id: String? = null,
        val title: String,
        val brand: String? = null,
        val category: String,
        val modalno: String,
        val price: String,
        val image: String,
        val shortdescription: String,
        val fulldescription: String,
        val productimages: String,
        val arrivaldate: String
)

data class DashboardProducts (
        @Id
        val id: String? = null,
        val title: String,
        val brand: String? = null,
        val category: String,
        val modalno: String,
        val price: String,
        val image: String,
        val productimages: String,
        val arrivaldate: String
)

data class ProductDescription (
        val image: String,
        var data: Products? = null,
        var imagearray: String?
)

@Document(collection="fs.files")
data class Files (
        @Id
        val id: String? = null,
        val filename: String
)

@Document(collection="fs.chunks")
data class Fileschunks (
        @Id
        val id: String? = null,
        val files_id: String? = null,
        val data: String
)

