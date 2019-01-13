package com.herokuapp.newtechserver2.data

import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime
import javax.persistence.Id

@Document(collection="orderrequests")
data class Orders (
        @Id
        val id: String? = null,

        var customerId: String,

        val orderId: String?,

        var totalamount: String?,

        var orderData: String,

        var requestdate: LocalDateTime

)