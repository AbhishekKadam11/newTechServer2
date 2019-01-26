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

    fun productCategoryList(category: String, brand:String?) =
            productDao.getCategoryProducts(category, brand)

    fun brandProductList(brand: String) =
            productDao.getBrandByName(brand)

    fun searchItem(searchKey: String, category: String) =
            productDao.getProductFromSearch(searchKey, category)
}

