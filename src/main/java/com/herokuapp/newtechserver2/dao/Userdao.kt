package com.herokuapp.newtechserver2.dao

import com.herokuapp.newtechserver2.Newtechserver2Application
import com.herokuapp.newtechserver2.data.Users
import com.herokuapp.newtechserver2.repository.UserRepository
import org.springframework.stereotype.Component
import org.slf4j.LoggerFactory
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder


@Component
class UserDao(
        private val userRepository: UserRepository
) {

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

}