package ru.leoltron.onmeeting.auth

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import ru.leoltron.onmeeting.model.database.User

class OnMeetingUserDetails(private val user: User) : UserDetails {

    override fun getAuthorities(): Collection<GrantedAuthority>? =
            user.authorities.map { a -> SimpleGrantedAuthority(a.authority) }.toList()

    override fun getPassword(): String = user.hash

    override fun getUsername(): String = user.username

    override fun isAccountNonExpired(): Boolean = true

    override fun isAccountNonLocked(): Boolean = true

    override fun isCredentialsNonExpired(): Boolean = true

    override fun isEnabled(): Boolean = true
}
