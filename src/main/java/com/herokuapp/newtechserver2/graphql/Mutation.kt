package com.herokuapp.newtechserver2.graphql

import com.coxautodev.graphql.tools.GraphQLMutationResolver
import com.herokuapp.newtechserver2.Newtechserver2Application
import com.herokuapp.newtechserver2.dao.NewProductDao
import com.herokuapp.newtechserver2.dao.Ordersdao
import com.herokuapp.newtechserver2.dao.Reviewdao
import com.herokuapp.newtechserver2.dao.UserDao
import com.herokuapp.newtechserver2.data.Orders
import com.herokuapp.newtechserver2.data.Products
import com.herokuapp.newtechserver2.data.Review
import com.herokuapp.newtechserver2.data.Users
import com.herokuapp.newtechserver2.graphql.input.NewProductInput
import com.herokuapp.newtechserver2.graphql.input.OrdersInput
import com.herokuapp.newtechserver2.graphql.input.ProductReviewInput
import com.herokuapp.newtechserver2.graphql.input.UserInput
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component
class Mutation(
        private val userDao: UserDao,
        private val newProductDao: NewProductDao,
        private val reviewdao: Reviewdao,
        private val ordersdao: Ordersdao
): GraphQLMutationResolver {

    private val logger = LoggerFactory.getLogger(Newtechserver2Application::class.java)

    fun signup(input: UserInput): Users? {
        val userData = userDao.createUser(input.email, input.password!!, input.profilename)
        return userData
    }

    fun newproduct(input: NewProductInput): Products? {
        val productData = newProductDao.newProductData(input.title, input.brand, input.category, input.modalno, input.price,
                input.arrivaldate, input.fulldescription, input.shortdescription, input.image, input.productimages)
        return productData
    }

    fun userBasicDetails(input: UserInput): Users? {
        val userData = userDao.updateUserBasicDetails(input.email, input?.profilePic, input.profilename, input?.address,
                input?.extraaddon, input?.firstName, input?.middleName, input?.lastName, input?.gender, input?.mobileno,
                input?.state, input?.city)
        return userData
    }

    fun productReview(input: ProductReviewInput): Review? {
        val result = reviewdao.setCustomerReview(input.productId, input.comment, input.starRate)
        return result
    }

    fun placeOrder(input: OrdersInput): Orders? {
        val result = ordersdao.setCustomerOrder(input.orderData, input.totalamount)
        return result
    }

    fun upload() {

      //  logger.info(file.toString())
    }

}