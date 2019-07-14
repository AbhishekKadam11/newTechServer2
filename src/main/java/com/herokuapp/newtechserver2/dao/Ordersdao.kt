package com.herokuapp.newtechserver2.dao

import com.herokuapp.newtechserver2.Newtechserver2Application
import com.herokuapp.newtechserver2.data.Orders
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
        private val ordersRepository: OrdersRepository
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

    fun getCustomerOrderDetails(): List<Orders> {
        val userid = tokenService.getUserIdFromtoken()
        logger.info(userid)
        if(userid.length != 0) {
            val listdata =  ordersRepository.findCustomerIdLike(userid)
            return listdata
        }
        return emptyList()
    }

}