package com.herokuapp.newtechserver2.dao

import com.google.gson.*
import com.google.gson.reflect.TypeToken
import com.herokuapp.newtechserver2.Newtechserver2Application
import com.herokuapp.newtechserver2.data.Products
import com.herokuapp.newtechserver2.repository.ProductRepository
import org.slf4j.LoggerFactory
import org.springframework.boot.configurationprocessor.json.JSONObject
import org.springframework.stereotype.Component
import com.herokuapp.newtechserver2.data.ProductList
import java.util.*

@Component
class ProductDao(private val productRepository: ProductRepository)  {
    private val logger = LoggerFactory.getLogger(Newtechserver2Application::class.java)
    var dashboardProduct = JSONObject()
    val motherboardArray = ArrayList<JSONObject>()
    val proceessorArray = ArrayList<JSONObject>()
    val graphicArray = ArrayList<JSONObject>()
    val monitorArray = ArrayList<JSONObject>()
    val routerArray = ArrayList<JSONObject>()
    val gson = Gson()


    fun getDashboardProducts(): List<Products> {
        val productListData:List<Products> = productRepository.findAll()
     //   val productListData = ProductList()
        for(item in productListData) {
            val product = JSONObject()
            if (item.category == "Motherboard") {
              //  var product = listOf(ProductItem(item.toString(), item.image))
                product.put("data", item.toString())
                product.put("image", item.image)
                motherboardArray.add(product)
            }
            if (item.category == "Processor") {
                product.put("data", item.toString())
                product.put("image", item.image)
                proceessorArray.add(product)
            }
            if (item.category == "Graphic Card") {
                product.put("data", item.toString())
                product.put("image", item.image)
                graphicArray.add(product)
            }
            if (item.category == "Monitor") {
                product.put("data", item.toString())
                product.put("image", item.image)
                monitorArray.add(product)
            }
            if (item.category == "Router") {
                product.put("data", item.toString())
                product.put("image", item.image)
                routerArray.add(product)
            }

        }
        if (motherboardArray.size !== 0) {
            dashboardProduct.put("motherboard", motherboardArray)
        }

      //  var test = JsonSerializer(dashboardProduct)
        var gson = Gson()

         var result: ProductList = gson.fromJson(motherboardArray.toString(), ProductList::class.java)
        logger.info(result.toString())
        //   productList.add(dashboardProduct)

        return productListData
    }

    fun getBrandByName(brand: String) =
            productRepository.findByBrandLike(brand)
    
}

data class ProductItem(var data:String, var image:String)