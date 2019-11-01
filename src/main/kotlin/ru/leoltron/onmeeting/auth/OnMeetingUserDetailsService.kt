package ru.leoltron.onmeeting.auth

import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import ru.leoltron.onmeeting.repo.UserRepository

@Service
class OnMeetingUserDetailsService(private val userRepository: UserRepository) : UserDetailsService {

    @Throws(UsernameNotFoundException::class)
    override fun loadUserByUsername(username: String): UserDetails {
        val user = userRepository.findByUsername(username).firstOrNull() ?: throw UsernameNotFoundException(username)
        return OnMeetingUserDetails(user)
    }
}
