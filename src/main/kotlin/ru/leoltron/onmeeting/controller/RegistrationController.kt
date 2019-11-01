package ru.leoltron.onmeeting.controller

import org.springframework.http.ResponseEntity
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController
import ru.leoltron.onmeeting.model.User
import ru.leoltron.onmeeting.repo.UserRepository

@RestController
class RegistrationController(private val userRepository: UserRepository, private val passwordEncoder: PasswordEncoder) : BaseController() {
    @PostMapping("/register")
    fun register(username: String, password: String): ResponseEntity<Any> = when {
        username.length <= 4 -> badRequest("Username length must be more than 4 characters")
        username.length > 50 -> badRequest("Username length must be 50 characters or less")
        password.isEmpty() -> badRequest("Password cannot be empty")
        userRepository.findByUsername(username).any() -> badRequest("Username is already taken")
        else -> {
            userRepository.save(User(username, passwordEncoder.encode(password)))
            ok()
        }
    }
}
