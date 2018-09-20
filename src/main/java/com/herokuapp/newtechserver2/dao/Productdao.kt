package com.herokuapp.newtechserver2.dao

import com.herokuapp.newtechserver2.data.Products
import com.herokuapp.newtechserver2.repository.ProductRepository
import org.springframework.stereotype.Component

@Component
class ProductDao( private val productRepository: ProductRepository) {


    fun getDashboardProducts(): List<Products> =
         productRepository.findAll()


    fun getBrandByName(brand: String) =
            productRepository.findByBrandLike(brand)
    
}