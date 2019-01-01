package com.herokuapp.newtechserver2.graphql

import com.coxautodev.graphql.tools.GraphQLMutationResolver
import com.herokuapp.newtechserver2.Newtechserver2Application
import com.herokuapp.newtechserver2.dao.NewProductDao
import com.herokuapp.newtechserver2.dao.UserDao
import com.herokuapp.newtechserver2.data.Products
import com.herokuapp.newtechserver2.data.Users
import com.herokuapp.newtechserver2.graphql.input.NewProductInput
import com.herokuapp.newtechserver2.graphql.input.UserInput
import org.apache.commons.fileupload.UploadContext
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import java.util.*
import javax.servlet.ServletResponse
import javax.servlet.http.HttpServletResponse;

@Component
class Mutation(
        private val userDao: UserDao,
        private val newProductDao: NewProductDao
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
                input?.extraaddon, input?.firstName, input?.middleName, input?.lastName, input?.gender, input?.mobileno)
        return userData
    }

    fun upload() {

      //  logger.info(file.toString())
    }

}