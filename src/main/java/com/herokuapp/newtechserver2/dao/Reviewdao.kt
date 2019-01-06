package com.herokuapp.newtechserver2.dao

import com.herokuapp.newtechserver2.data.Review
import com.herokuapp.newtechserver2.repository.ReviewRepository
import com.herokuapp.newtechserver2.repository.UserRepository
import com.herokuapp.newtechserver2.service.TokenService
import org.springframework.stereotype.Component
import java.time.LocalDateTime

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
        return reviewData
    }

    fun setCustomerReview(productId: String, comment: String, starRate: String): Review? {
        val userid = tokenService.getUserIdFromtoken()
        if(userid.length != 0) {
            return reviewRepository.save(Review(customerId=userid, productId = productId,
                    comment = comment,starRate = starRate, postDate = LocalDateTime.now().toString()))
        }
        return null
    }
}