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
        private val logger = LoggerFactory.getLogger(Newtechserver2Application::class.java)

        fun getUserById(id: String) =
                userRepository.findById(id)

        fun getUserByEmail(email: String) =
                userRepository.findByEmailLike(email)

        fun getUserForLogin(email: String, password: String): Users? {
                try {
                        val getUser =  userRepository.findByEmailLike(email)
                        val authenticate = validatePassword(password, getUser.password)
                        if (authenticate) {
                                val jwt = tokenService.createToken(getUser.id!!)
                                getUser.also { getUser -> getUser.token = jwt }
                                return getUser
                        }
                } catch (e: Exception) {
                        return error("user not found")
                }
                return error("user not found")
        }

        fun encryptPassword(password: String): String {
                val passwordEncoder = BCryptPasswordEncoder()
                val hashedPassword = passwordEncoder.encode(password)
             //   logger.info(hashedPassword)
                return hashedPassword
        }

        fun validatePassword(password: String ,hashedPassword: String?): Boolean {
                val encoder = BCryptPasswordEncoder().matches(password, hashedPassword)
                return encoder
        }

        fun createUser(email: String, password: String, profilename: String): Users? {
                val hashedpassword = encryptPassword(password)
                var result = userRepository.save(Users(email = email, password = hashedpassword, profilename = profilename))
                val id = result.id
                val jwt = tokenService.createToken(id!!)
                result.also { result -> result.token = jwt }
                return result
        }

        fun getUserBasicDetails(): Users {
                val userid = tokenService.getUserIdFromtoken()
                val data = userRepository.findById(userid)
                return data
        }

        fun updateUserBasicDetails(email: String, profilePic: String?, profilename: String, address: String?,
                                   extraaddon: String?, firstName: String?, middleName: String?, lastName: String?,
                                   gender: String?, mobileno: String?): Users? {
                val userid = tokenService.getUserIdFromtoken()
                var userData = userRepository.findById(userid)
                return userRepository.save(userData.apply {
                        userData.email = email; userData.profilename = profilename;
                        userData.extraaddon = extraaddon; userData.firstName = firstName; userData.middleName = middleName;
                        userData.lastName = lastName; userData.gender = gender; userData.mobileno = mobileno;
                        userData.address = address
                })
        }

}