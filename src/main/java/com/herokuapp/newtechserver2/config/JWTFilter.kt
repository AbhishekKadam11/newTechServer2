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
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With, remember-me, application/json");

        if ("OPTIONS".equals(request.getMethod(), ignoreCase = true)) {
            response.sendError(HttpServletResponse.SC_OK, "success")
            return
        }

        if(token != null) {
            val bearer = token.split(" ");
            val bearerToken = bearer[1];
            tokenService.getUserIdFromToken(bearerToken)
            filterChain.doFilter(req, res)
        } else {
            response.setStatus(HttpServletResponse.SC_OK)
            filterChain.doFilter(req, res)
        }

//        if (allowRequestWithoutToken(request)) {
//            response.setStatus(HttpServletResponse.SC_OK)
//            filterChain.doFilter(req, res)
//        } else {
//            if (token == null || !tokenService.isTokenValid(token)) {
//                response.sendError(HttpServletResponse.SC_UNAUTHORIZED)
//            } else {
//                val userId = ObjectId(tokenService.getUserIdFromToken(token))
//                request.setAttribute("userId", userId)
//                filterChain.doFilter(req, res)
//
//            }
//        }
    }

//    fun allowRequestWithoutToken(request: HttpServletRequest): Boolean {
//        if (request.getRequestURI().contains("/")) {
//            return true
//        }
//        return false
//    }

//    internal constructor()
//
//    init {
//        return true
//    }
}