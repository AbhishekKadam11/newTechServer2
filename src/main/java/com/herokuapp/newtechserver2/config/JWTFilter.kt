package com.herokuapp.newtechserver2.config

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.GenericFilterBean;
import com.herokuapp.newtechserver2.service.TokenService

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
open class JWTFilter internal constructor() : GenericFilterBean() {

    private val tokenService: TokenService

    init {
        this.tokenService = TokenService()
    }

    @Throws(IOException::class, ServletException::class)
    override fun doFilter(req: ServletRequest, res: ServletResponse, filterChain: FilterChain) {
        val request = req as HttpServletRequest
        val response = res as HttpServletResponse
        val token = request.getHeader("Authorization")
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Credentials", "true")
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE")
        response.setHeader("Access-Control-Max-Age", "3600")
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization, Content-Length, X-Requested-With");

        if ("OPTIONS".equals(request.getMethod(), ignoreCase = true)) {
            response.sendError(HttpServletResponse.SC_OK, "success")
            return
        }

        if (token != null) {
            val bearer = token.split(" ")
            val bearerToken = bearer[1]
            tokenService.getUserFromToken(bearerToken)
            filterChain.doFilter(req, res)
        } else {
            response.setStatus(HttpServletResponse.SC_OK)
            filterChain.doFilter(req, res)
        }


    }
}
