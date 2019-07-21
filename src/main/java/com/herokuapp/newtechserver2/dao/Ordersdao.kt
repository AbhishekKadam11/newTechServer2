package com.herokuapp.newtechserver2.dao

import com.google.gson.Gson
import com.herokuapp.newtechserver2.Newtechserver2Application
import com.herokuapp.newtechserver2.data.Orders
import com.herokuapp.newtechserver2.data.ProductShortDetails
import com.herokuapp.newtechserver2.data.Products
import com.herokuapp.newtechserver2.data.productOrderDetails
import com.herokuapp.newtechserver2.repository.OrdersRepository
import com.herokuapp.newtechserver2.repository.UserRepository
import com.herokuapp.newtechserver2.service.TokenService
import org.slf4j.LoggerFactory
import org.springframework.boot.configurationprocessor.json.JSONArray
import org.springframework.boot.configurationprocessor.json.JSONObject
import org.springframework.stereotype.Component
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Component
class Ordersdao(
        private val userRepository: UserRepository,
        private val tokenService: TokenService,
        private val ordersRepository: OrdersRepository,
        private val productDao: ProductDao
) {
    private val logger = LoggerFactory.getLogger(Newtechserver2Application::class.java)

    fun setCustomerOrder(orderData: String, totalamount: String): Orders? {
        val userid = tokenService.getUserIdFromtoken()
        if(userid.length != 0) {
            return ordersRepository.save(Orders(customerId =userid, orderData = orderData,
                    totalamount = totalamount, orderId = (userid + LocalDateTime.now().format(DateTimeFormatter.BASIC_ISO_DATE)).toString(),
                    requestdate = LocalDateTime.now()))
        }
        return null
    }

    fun getCustomerOrderDetails(): List<ProductShortDetails>? {
        val userid = tokenService.getUserIdFromtoken()
     //   logger.info(userid)
     //   var productData = listOf<Products>()
        var productData:ArrayList<ProductShortDetails>?=null
        if(userid.length != 0) {
            val listdata =  ordersRepository.findCustomerIdLike(userid)
            for (p in listdata) {

               // val listOfStrings: List<Orders> = Gson().fromJson(p.orderData,  Array<Orders>::class.java).toList()
                val orderedProduct: List<productOrderDetails> =  Gson().fromJson(p.orderData,  Array<productOrderDetails>::class.java).toList()
              //  var data = orderedProduct.get("")

                for (item in orderedProduct) {
                    var productDescription = productDao.getProductDescriptionData(item.productId)
                  //  productData.add
                  // productData.add(productDescription)
                    logger.info(productDescription.image.toString())
                  //  productData.add(0,productDescription)
                }
           //     logger.info(orderedProduct.toString())
//                for (data in p.orderData) {
//                    logger.info("data" + data)
//                }
            }

            return productData
        }
        return null
    }

}