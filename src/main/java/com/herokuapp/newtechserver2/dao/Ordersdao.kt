package com.herokuapp.newtechserver2.dao

import com.google.gson.Gson
import com.herokuapp.newtechserver2.Newtechserver2Application
import com.herokuapp.newtechserver2.data.*
import com.herokuapp.newtechserver2.repository.OrdersRepository
import com.herokuapp.newtechserver2.repository.UserRepository
import com.herokuapp.newtechserver2.service.TokenService
import org.slf4j.LoggerFactory
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
            val orderedProduct: List<productOrderDetails> = Gson().fromJson(orderData, Array<productOrderDetails>::class.java).toList()
            logger.info(orderedProduct.toString())
            return ordersRepository.save(Orders(customerId =userid, orderData = orderedProduct,
                    totalamount = totalamount, orderId = (userid + LocalDateTime.now().format(DateTimeFormatter.BASIC_ISO_DATE)).toString(),
                    requestdate = LocalDateTime.now()))
        }
        return null
    }

    fun getCustomerOrderDetails(): ArrayList<ProductOrdered> {
        val userid = tokenService.getUserIdFromtoken()
        var productData = ArrayList<ProductOrdered>()
        if (userid.length != 0) {
            val listdata = ordersRepository.findCustomerIdLike(userid)
            for (p in listdata) {
//                logger.info(p.toString())
//                val orderedProduct: List<productOrderDetails> = Gson().fromJson(p.orderData, Array<productOrderDetails>::class.java).toList()
                val orderedProduct: List<productOrderDetails> = p.orderData
                for (item in orderedProduct) {
                    var productDescription = productDao.getProductDescriptionData(item.productId)
//                    logger.info(productDescription.image.toString())
                    productData.add(ProductOrdered(p.orderId, p.totalamount, p.requestdate.toString(), productDescription))
                }
            }
        }
        return productData
    }

}