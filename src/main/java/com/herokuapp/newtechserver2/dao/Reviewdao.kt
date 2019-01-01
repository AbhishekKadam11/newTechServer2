package com.herokuapp.newtechserver2.dao

import com.herokuapp.newtechserver2.data.Review
import com.herokuapp.newtechserver2.repository.ReviewRepository
import com.herokuapp.newtechserver2.repository.UserRepository
import com.herokuapp.newtechserver2.service.TokenService
import org.springframework.stereotype.Component

@Component
class Reviewdao(
        private val reviewRepository: ReviewRepository,
        private val userRepository: UserRepository,
        private val tokenService: TokenService
) {
    fun getCustomerReviewData(productId: String): List<Review> {
        var reviewData =  reviewRepository.findByProductIdLike(productId)
        reviewData.forEach {

            try {
                val customerName = userRepository.findById(it.customerId)
                it.name = customerName.profilename
            }catch (e: Exception) {

            }

        }
      //  reviewData.filter { userRepository.findById(it.customerId)  }
     //   var customerNames =
        return reviewData
    }

}