package ru.leoltron.onmeeting.repo

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import ru.leoltron.onmeeting.model.database.Tag

@Repository
interface TagRepository : CrudRepository<Tag, Int> {
    fun findByName(name: String): Iterable<Tag>
}

