package com.herokuapp.newtechserver2.data

import org.springframework.data.mongodb.core.mapping.Document
import java.util.*
import javax.persistence.Id

@Document(collection="cities")
data class Cities(
        val name: String,
        val state: String
)