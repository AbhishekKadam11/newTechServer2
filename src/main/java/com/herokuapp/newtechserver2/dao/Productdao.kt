package com.herokuapp.newtechserver2.dao

import com.google.gson.*
import com.herokuapp.newtechserver2.Newtechserver2Application
import com.herokuapp.newtechserver2.data.ProductDescription
import com.herokuapp.newtechserver2.data.Products
import com.herokuapp.newtechserver2.repository.ProductRepository
import org.slf4j.LoggerFactory
import org.springframework.boot.configurationprocessor.json.JSONObject
import org.springframework.stereotype.Component
import java.util.*

@Component
class ProductDao( private val productRepository: ProductRepository )  {
    private val logger = LoggerFactory.getLogger(Newtechserver2Application::class.java)
    var dashboardProduct = JSONObject()
    val motherboardArray = ArrayList<JSONObject>()
    val proceessorArray = ArrayList<JSONObject>()
    val graphicArray = ArrayList<JSONObject>()
    val monitorArray = ArrayList<JSONObject>()
    val routerArray = ArrayList<JSONObject>()
    val gson = Gson()

    fun getDashboardProducts(): JSONObject {
        val productListData:List<Products> = productRepository.findAll()

        for(item in productListData) {
            val product = JSONObject()
            val jsonString = gson.toJson(Products(item.id,item.title, item.brand, item.category,
                    item.modalno, item.price, item.image, item.shortdescription, item.fulldescription, item.productimages,
                    item.arrivaldate))
            if (item.category == "Motherboard") {
                product.put("data", JSONObject(jsonString))
                product.put("image", item.image)
                motherboardArray.add(product)
            }
            if (item.category == "Processor") {
                product.put("data", JSONObject(jsonString))
                product.put("image", item.image)
                proceessorArray.add(product)
            }
            if (item.category == "Graphic Card") {
                product.put("data", JSONObject(jsonString))
                product.put("image", item.image)
                graphicArray.add(product)
            }
            if (item.category == "Monitor") {
                product.put("data", JSONObject(jsonString))
                product.put("image", item.image)
                monitorArray.add(product)
            }
            if (item.category == "Router") {
                product.put("data", JSONObject(jsonString))
                product.put("image", item.image)
                routerArray.add(product)
            }

        }
        if (motherboardArray.size !== 0) {
            dashboardProduct.put("motherboard", motherboardArray)
        }
        if (proceessorArray.size !== 0) {
            dashboardProduct.put("Processor", proceessorArray)
        }
        if (graphicArray.size !== 0) {
            dashboardProduct.put("Graphic Card", graphicArray)
        }
        if (monitorArray.size !== 0) {
            dashboardProduct.put("Monitor", monitorArray)
        }
        if (routerArray.size !== 0) {
            dashboardProduct.put("Router", routerArray)
        }
        //   var result: ProductList = gson.fromJson(motherboardArray.toString(), ProductList::class.java)
        //  logger.info(result.toString())
        return dashboardProduct
    }


    fun getProductDescriptionData(pid: String): ProductDescription {
        val productData = productRepository.findById(pid)
        val pdata = getProductData(pid)
        productData.data = pdata
        productData.imagearray = pdata.productimages
        return productData
    }

    fun getProductData(pid: String): Products{
        val productInformation = productRepository.findProductById(pid)

        return productInformation
    }

    fun getBrandByName(brand: String) =
            productRepository.findByBrandLike(brand)



}

