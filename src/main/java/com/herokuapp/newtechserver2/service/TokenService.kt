package com.herokuapp.newtechserver2.service

import com.auth0.jwt.exceptions.JWTVerificationException
import java.io.UnsupportedEncodingException
import com.auth0.jwt.JWT
import com.auth0.jwt.exceptions.JWTCreationException
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.bson.types.ObjectId;
import java.util.*
import org.springframework.stereotype.Service;

var userId: String = ""

@Service
open class TokenService {


    fun createToken(userId: String): String? {
        try {
            val algorithm = Algorithm.HMAC256(TOKEN_SECRET)
            return JWT.create()
                    .withClaim("userId", userId)
                    .withClaim("createdAt", Date())
                    .sign(algorithm)
        } catch (exception: UnsupportedEncodingException) {
            exception.printStackTrace()
            //log WRONG Encoding message
        } catch (exception: JWTCreationException) {
            exception.printStackTrace()
            //log Token Signing Failed
        }

        return null
    }

    fun getUserFromToken(token: String): String? {
        try {
            val algorithm = Algorithm.HMAC256(TOKEN_SECRET)
            val verifier = JWT.require(algorithm)
                    .build()
            val jwt = verifier.verify(token)
            val id = jwt.getClaim("userId").asString()
            return setUserIdFromtoken(id).toString()
        } catch (exception: UnsupportedEncodingException) {
            exception.printStackTrace()
            //log WRONG Encoding message
            return null
        } catch (exception: JWTVerificationException) {
            exception.printStackTrace()
            //log Token Verification Failed
            return null
        }

    }

    fun setUserIdFromtoken(userid: String) {
        userId = userid
    }

    fun getUserIdFromtoken(): String {
        return userId
    }

    fun isTokenValid(token: String): Boolean {
        val userId = this.getUserFromToken(token)
        return userId != null
    }


    companion object {
        val TOKEN_SECRET = "AbhishekIsAwesome"
    }
}