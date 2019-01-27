package com.herokuapp.newtechserver2.repository

import com.herokuapp.newtechserver2.data.ProductDescription
import com.herokuapp.newtechserver2.data.Products
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.mongodb.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface ProductRepository : MongoRepository<Products, Any> {

    fun findByBrandLike(brand: String): List<Products>

    fun findProductById(pid: String): Products

    fun findByCategoryAndBrandLike(category: String, brand: String?): List<Products>

    fun findById(pid: String): ProductDescription

    @Query(value = "{'title': {\$regex : ?0, \$options: 'i'},'brand': {\$regex : ?0, \$options: 'i'}}}")
    fun findByQuery(searchKey: String): List<Products>

    @Query("{'\$or' : [{'title': {\$regex : ?0, \$options: 'i'},'brand': {\$regex : ?0, \$options: 'i'}}], \$and: [{ 'category': {\$in: ?1} }]}")
    fun findByCategoryQuery(searchKey: String, category: Array<String>): List<Products>
}