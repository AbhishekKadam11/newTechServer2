package com.herokuapp.newtechserver2.dao

import com.google.gson.*
import com.herokuapp.newtechserver2.Newtechserver2Application
import com.herokuapp.newtechserver2.data.DashboardProducts
import com.herokuapp.newtechserver2.data.ProductDescription
import com.herokuapp.newtechserver2.data.Products
import com.herokuapp.newtechserver2.repository.ProductRepository
import org.slf4j.LoggerFactory
import org.springframework.boot.configurationprocessor.json.JSONObject
import org.springframework.stereotype.Component
import kotlin.collections.ArrayList

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
            val jsonString = gson.toJson(DashboardProducts(item.id,item.title, item.brand, item.category,
                    item.modalno, item.price, item.image, item.productimages,
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
            dashboardProduct.put("Motherboard", motherboardArray)
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

    fun getCategoryProducts(category: String, brand: String?): List<Products> {
        if (brand !== "" && brand !== null) {
            var brandArray = brand!!.replace("[", "").replace("]", "")
            var data = brandArray.trim().split(",").map { it -> it.trim() }.toTypedArray()
            return productRepository.findByCategoryAndBrandIn(category, data)
        } else {
            return productRepository.findByCategoryLike(category)
        }
    }

    fun getProductFromSearch(searchKey: String, category:String): List<Products> {
        if(category.isNotEmpty()) {
            var categoryArray = category.replace("[","").replace("]","")
            var data = categoryArray.trim().split(",").map { it -> it.trim() }.toTypedArray()
            var result =  productRepository.findByCategoryQuery(searchKey, data)
            categoryArray = ""
            return result
        } else{
            return productRepository.findByQueryLike(searchKey)
        }
    }






}

