package com.herokuapp.newtechserver2.dao

import com.herokuapp.newtechserver2.repository.ProductRepository
import org.springframework.stereotype.Component

@Component
class ProductDao( private val productRepository: ProductRepository) {


    fun getDashboardProducts() {
         productRepository.findByProducts()
    }

    fun getBrandByName(brand: String) =
            productRepository.findByBrandLike(brand)
    
}