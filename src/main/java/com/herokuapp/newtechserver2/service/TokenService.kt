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

    fun getUserIdFromToken(token: String): String? {
        try {
            val algorithm = Algorithm.HMAC256(TOKEN_SECRET)
            val verifier = JWT.require(algorithm)
                    .build()
            val jwt = verifier.verify(token)
            return jwt.getClaim("userId").asString()
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

    fun isTokenValid(token: String): Boolean {
        val userId = this.getUserIdFromToken(token)
        return userId != null
    }

    companion object {

        val TOKEN_SECRET = "AbhishekIsAwesome"
    }
}