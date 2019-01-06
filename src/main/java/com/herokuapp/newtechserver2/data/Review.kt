package com.herokuapp.newtechserver2.data

import org.springframework.data.mongodb.core.mapping.Document
import javax.persistence.Id

@Document(collection="customerreviews")
data class Review (
        @Id
        val id: String? = null,

        var customerId: String,

        val starRate: String? = null,

        var postDate: String? = null,

        var comment: String,

        var productId: String

) {
    var name: String? = null
}
