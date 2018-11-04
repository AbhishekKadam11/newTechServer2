package com.herokuapp.newtechserver2.dao

import com.herokuapp.newtechserver2.data.Products
import com.herokuapp.newtechserver2.repository.NewProductRepository
import com.herokuapp.newtechserver2.service.TokenService

import org.springframework.stereotype.Component

@Component
class NewProductDao(
        private val newProductRepository: NewProductRepository
) {
        fun newProductData(title: String, brand: String, category: String, modalno: String, price: String, arrivaldate: String,
                           fulldescription: String, shortdescription:String, image: String, productimages:String):Products? {
            return newProductRepository.save(Products(title = title, brand = brand, category = category,
                    modalno=modalno, price=price, arrivaldate=arrivaldate, fulldescription=fulldescription, shortdescription=shortdescription,
                    image=image, productimages=productimages))

        }
}