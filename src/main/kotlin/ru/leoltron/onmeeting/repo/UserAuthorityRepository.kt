package ru.leoltron.onmeeting.repo

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import ru.leoltron.onmeeting.model.UserAuthority

@Repository
interface UserAuthorityRepository : CrudRepository<UserAuthority, Int> {
    fun findByUserId(userId: Int): Iterable<UserAuthority>
}