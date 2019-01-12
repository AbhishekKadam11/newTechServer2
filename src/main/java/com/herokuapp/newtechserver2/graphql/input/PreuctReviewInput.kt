package com.herokuapp.newtechserver2.graphql.input

data class ProductReviewInput(

        val starRate: String,
        val comment: String,
        val productId: String
)