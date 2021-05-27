package com.herokuapp.newtechserver2.repository

import com.herokuapp.newtechserver2.data.ProductDescription
import com.herokuapp.newtechserver2.data.Files
import org.springframework.data.mongodb.core.query.Query.query
import org.springframework.data.mongodb.gridfs.GridFsCriteria
import org.springframework.data.mongodb.gridfs.GridFsOperations
import org.springframework.data.mongodb.gridfs.GridFsTemplate
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.mongodb.repository.Query
import org.springframework.stereotype.Repository


@Repository
interface FileRepository : MongoRepository<Files, Any> {

//    val gridFsTemplate: GridFsTemplate?
//
//    val gridOperations: GridFsOperations?

//    fun findFilename(name: String): org.springframework.data.mongodb.core.query.Query {
//
//        return org.springframework.data.mongodb.core.query.Query.query(GridFsCriteria.whereFilename().`is`(name))
//    }

//    fun findFileByFilename(id: String): String

//    @Query(value = "{'\$or' : [{'filename': {\$regex : ?0, \$options: 'i'}}]}")
    fun findByFilename(filename: String): List<String>
}