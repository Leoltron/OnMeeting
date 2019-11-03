package ru.leoltron.onmeeting.repo

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import ru.leoltron.onmeeting.model.database.Card

@Repository
interface CardRepository : CrudRepository<Card, Int>