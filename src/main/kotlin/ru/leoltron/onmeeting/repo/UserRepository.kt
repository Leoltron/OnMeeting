package ru.leoltron.onmeeting.repo

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import ru.leoltron.onmeeting.model.database.User

@Repository
interface UserRepository : CrudRepository<User, Int> {
    fun findByUsername(username: String): Iterable<User>
}

