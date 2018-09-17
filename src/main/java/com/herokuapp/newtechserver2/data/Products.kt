package com.herokuapp.newtechserver2.data

import org.springframework.data.mongodb.core.mapping.Document
import javax.persistence.Id

@Document(collection="productuploads")
data class Products (
        @Id
        val id: String,
        val title: String,
        val brand: String
)