package com.herokuapp.newtechserver2.repository

import com.herokuapp.newtechserver2.data.ProductDescription
import com.herokuapp.newtechserver2.data.Products
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface ProductRepository : MongoRepository<Products, Any> {

   // fun findByProductLike(): List<Products>
//    fun findByNameLike(): List<Products>
    fun findByBrandLike(brand: String): List<Products>

    fun findById(pid: String): ProductDescription

   // fun findByProducts(): List<Products>


}