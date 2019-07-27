package com.herokuapp.newtechserver2.data

import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime
import javax.persistence.Id

@Document(collection = "orderrequests")
data class Orders(
        @Id
        val id: String? = null,

        var customerId: String,

        val orderId: String?,

        var totalamount: String?,

        var orderData: String,

        var requestdate: LocalDateTime? = null

)

data class productOrderDetails(
        // [{productId=5a81b6d55bfc8339202a218d, quantity=1, price=18000}]
        val productId: String,
        val quantity: Int,
        val price: Int
)

data class ProductOrdered(
        val orderId: String?,
        val totalamount: String?,
        val requestdate: String?,
        val productDescription: ProductDescription
)