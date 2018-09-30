package com.herokuapp.newtechserver2.dao

import com.google.gson.Gson
import com.herokuapp.newtechserver2.Newtechserver2Application
import com.herokuapp.newtechserver2.data.Products
import com.herokuapp.newtechserver2.repository.ProductRepository
import org.slf4j.LoggerFactory
import org.springframework.boot.configurationprocessor.json.JSONObject
import org.springframework.stereotype.Component

@Component
class ProductDao(private val productRepository: ProductRepository)  {
    private val logger = LoggerFactory.getLogger(Newtechserver2Application::class.java)
    val dashboardProduct = JSONObject()
    val motherboardArray = ArrayList<String>()
    val proceessorArray = ArrayList<String>()
    val graphicArray = ArrayList<String>()
    val monitorArray = ArrayList<String>()
    val routerArray = ArrayList<String>()
    val gson = Gson()

    fun getDashboardProducts(): MutableList<Products> {
        val productList = productRepository.findAll()
        for(item in productList) {
            val product = JSONObject()
            if (item.category == "Motherboard") {
              //  var product = listOf(ProductItem(item.toString(), item.image))
                product.put("data", item.toString())
                product.put("image", item.image)
                motherboardArray.add(product.toString())
            }
            if (item.category == "Processor") {
                product.put("data", item.toString())
                product.put("image", item.image)
                proceessorArray.add(product.toString())
            }
            if (item.category == "Graphic Card") {
                product.put("data", item.toString())
                product.put("image", item.image)
                graphicArray.add(product.toString())
            }
            if (item.category == "Monitor") {
                product.put("data", item.toString())
                product.put("image", item.image)
                monitorArray.add(product.toString())
            }
            if (item.category == "Router") {
                product.put("data", item.toString())
                product.put("image", item.image)
                routerArray.add(product.toString())
            }

        }
        if (motherboardArray.size !== 0) {
            dashboardProduct.put("Motherboard", motherboardArray)
        }
       // val ProductItems: Products = gson.fromJson(dashboardProduct, Products::class.java)
   //     val dashboardItem : List<Products> = gson.fromJson(dashboardProduct.toList()
        //   productList.add(dashboardProduct)
        logger.info(motherboardArray.toString())
        return productList
    }

    fun getBrandByName(brand: String) =
            productRepository.findByBrandLike(brand)
    
}

data class ProductItem(var data:String, var image:String)