package com.herokuapp.newtechserver2.repository

import com.herokuapp.newtechserver2.data.ProductDescription
import com.herokuapp.newtechserver2.data.Products
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface ProductRepository : MongoRepository<Products, Any> {

    fun findByBrandLike(brand: String): List<Products>

    fun findProductById(pid: String): Products

    fun findByCategoryAndBrandLike(category: String, brand: String?): List<Products>

    fun findById(pid: String): ProductDescription

}