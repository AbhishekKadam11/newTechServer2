package com.herokuapp.newtechserver2.dao

import com.herokuapp.newtechserver2.Newtechserver2Application
import com.herokuapp.newtechserver2.data.Users
import com.herokuapp.newtechserver2.service.TokenService
import com.herokuapp.newtechserver2.repository.UserRepository
import org.springframework.stereotype.Component
import org.slf4j.LoggerFactory


import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

@Component
class UserDao(
        private val userRepository: UserRepository,
        private val tokenService: TokenService
) {
        private val expiration: Long = 100L
        private val secret = "AbhishekIsAwesome"
        private val header = "Authorization"
        private val logger = LoggerFactory.getLogger(Newtechserver2Application::class.java)

        fun getUserById(id: String) =
                userRepository.findById(id)

//        fun getUserByName(name: String) =
//                userRepository.findByNameLike(name)

        fun getUserByEmail(email: String) =
                userRepository.findByEmailLike(email)

        fun getUserForLogin(email: String, password: String): List<Users>
        {
                val hashedpassword = encryptPassword(password)
              //  val authenticate = validatePassword(password, hashedpassword)
            //    if (authenticate) {
                        return userRepository.findByEmail(email, hashedpassword)
              //  }

        }

        fun encryptPassword(password: String): String {
                val passwordEncoder = BCryptPasswordEncoder()
                val hashedPassword = passwordEncoder.encode(password)
                logger.info(hashedPassword);
                return hashedPassword;
        }

//        fun validatePassword(password: String ,hashedPassword: String): Boolean {
//                val encoder = BCryptPasswordEncoder()
//                return encoder.matches(password, hashedPassword)
//        }

        fun createUser(email: String, password: String, profilename: String): Users? {
                val hashedpassword = encryptPassword(password)
                var result = userRepository.save(Users(email = email, password = hashedpassword, profilename = profilename))
                val id = result.id
                val jwt = tokenService.createToken(id!!)
                result.also { result -> result.token = jwt }
                 logger.info(result.toString())
                return result
        }

   //     fun createJwt(id: String?): String {
//                val claims = HashMap<String, Any>()
//                claims.put("id", id!!)
//                return io.jsonwebtoken.Jwts.builder()
//                        .setClaims(claims)
//                        .setSubject(id)
//                     //   .setExpiration(java.util.Date(java.util.Date().time + java.util.concurrent.TimeUnit.HOURS.toMillis(expiration)))
//                        .signWith(io.jsonwebtoken.SignatureAlgorithm.HS256, secret).compact()

//        }


}