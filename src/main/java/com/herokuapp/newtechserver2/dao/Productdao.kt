package com.herokuapp.newtechserver2.dao

import com.herokuapp.newtechserver2.Newtechserver2Application
import com.herokuapp.newtechserver2.data.Products
import com.herokuapp.newtechserver2.repository.ProductRepository
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component
class ProductDao( private val productRepository: ProductRepository) {
    private val logger = LoggerFactory.getLogger(Newtechserver2Application::class.java)
    val dashboardProduct = {}
    val motherboardArray = ArrayList<String>()
    val proceessorArray = arrayOf<String>()
    val graphicArray = arrayOf<String>()
    val monitorArray = arrayOf<String>()
    val routerArray = arrayOf<String>()

    fun getDashboardProducts(): List<Products> {
        val productList = productRepository.findAll()
        for(item in productList) {
            var product = {}
            if (item.category === "Motherboard") {
            //    product.data = {var data = item}
                product = {var image = item.image}

                motherboardArray.add(product.toString())
            }
//            if (item['category'] === 'Processor') {
//                product['data'] = item;
//                product['image'] = item['image'];
//                proceessorArray.push(product);
//            }
//            if (item['category'] === 'Graphic Card') {
//                product['data'] = item;
//                product['image'] = item['image'];
//                graphicArray.push(product);
//            }
//            if (item['category'] === 'Monitor') {
//                product['data'] = item;
//                product['image'] = item['image'];
//                monitorArray.push(product);
//            }
//            if (item['category'] === 'Router') {
//                product['data'] = item;
//                product['image'] = item['image'];
//                routerArray.push(product);
//            }

        }
        logger.info(motherboardArray.toString())
        return productList
    }

    fun getBrandByName(brand: String) =
            productRepository.findByBrandLike(brand)
    
}