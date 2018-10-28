package com.herokuapp.newtechserver2.graphql.query

import com.coxautodev.graphql.tools.GraphQLQueryResolver
import com.herokuapp.newtechserver2.dao.ProductDao
import org.springframework.stereotype.Component

@Component
class ProductQueryResolver(
        private val productDao: ProductDao
): GraphQLQueryResolver {
    fun dashboardProductList() =
            productDao.getDashboardProducts()

    fun getProductDescriptionData(pid: String) =
            productDao.getProductDescriptionData(pid)

    fun brandProductList(brand: String) =
            productDao.getBrandByName(brand)
}

