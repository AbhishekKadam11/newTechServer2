package com.herokuapp.newtechserver2.dao

import com.herokuapp.newtechserver2.data.Cities
import com.herokuapp.newtechserver2.repository.StateRepository
import org.springframework.stereotype.Component

@Component
class StateDao(
        private val stateRepository: StateRepository

) {
    fun getStateListData(): List<Cities> {
        return stateRepository.findAll()

    }

    fun getCityListData(state: String): List<Cities> {
        return stateRepository.findByStateLike(state)

    }

//    fun setCustomerReview(productId: String, comment: String, starRate: String): Review? {
//        val userid = tokenService.getUserIdFromtoken()
//        if(userid.length != 0) {
//            return reviewRepository.save(Review(customerId=userid, productId = productId,
//                    comment = comment,starRate = starRate, postDate = LocalDateTime.now().toString()))
//        }
//        return null
//    }
}