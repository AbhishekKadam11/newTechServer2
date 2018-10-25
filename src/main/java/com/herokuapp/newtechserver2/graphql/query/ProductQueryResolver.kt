package com.herokuapp.newtechserver2.graphql.query

import com.coxautodev.graphql.tools.GraphQLQueryResolver
import com.herokuapp.newtechserver2.dao.ProductDao
import com.herokuapp.newtechserver2.data.ProductDescription
import com.herokuapp.newtechserver2.data.Products
import org.springframework.boot.configurationprocessor.json.JSONObject
import org.springframework.stereotype.Component

@Component
class ProductQueryResolver(
        private val productDao: ProductDao
): GraphQLQueryResolver {
    fun dashboardProductList() =
            productDao.getDashboardProducts()

    fun productDescriptionData(pid: String): ProductDescription =
            productDao.getProductDescriptionData(pid)

    fun brandProductList(brand: String) =
            productDao.getBrandByName(brand)
}