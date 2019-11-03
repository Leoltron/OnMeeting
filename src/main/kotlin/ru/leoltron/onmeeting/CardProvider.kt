package ru.leoltron.onmeeting

import org.springframework.stereotype.Component
import ru.leoltron.onmeeting.model.CardViewModel
import ru.leoltron.onmeeting.model.database.Card
import ru.leoltron.onmeeting.util.toModel
import javax.persistence.EntityManager
import javax.persistence.EntityManagerFactory
import javax.persistence.EntityTransaction

@Component
class CardProvider(private val entityManagerFactory: EntityManagerFactory) : ICardProvider {

    override fun getByTag(tagId: Int): List<CardViewModel> {
        val cards: List<Card>
        var entityManager: EntityManager? = null
        var transaction: EntityTransaction? = null
        try {
            entityManager = entityManagerFactory
                    .createEntityManager()
            transaction = entityManager!!.transaction
            transaction!!.begin()

            cards = entityManager.createQuery(
                    "SELECT c FROM card c " +
                            "INNER JOIN c.tags AS t " +
                            "WHERE t.tagId = :tagId", Card::class.java)
                    .setParameter("tagId", tagId)
                    .resultList

            transaction.commit()
        } catch (e: Throwable) {
            if (transaction != null && transaction.isActive)
                transaction.rollback()
            throw e
        } finally {
            entityManager?.close()
        }

        return cards.map(Card::toModel).toList()
    }

    override fun getByTagAndParticipant(userId: Int, tagId: Int): List<CardViewModel> {
        val cards: List<Card>
        var entityManager: EntityManager? = null
        var transaction: EntityTransaction? = null
        try {
            entityManager = entityManagerFactory
                    .createEntityManager()
            transaction = entityManager!!.transaction
            transaction!!.begin()

            cards = entityManager.createQuery(
                    "SELECT c FROM card c " +
                            "INNER JOIN c.tags AS t " +
                            "INNER JOIN c.participants AS p " +
                            "WHERE t.tagId = :tagId AND p.userId = :userId", Card::class.java)
                    .setParameter("tagId", tagId)
                    .setParameter("userId", userId)
                    .resultList

            transaction.commit()
        } catch (e: Throwable) {
            if (transaction != null && transaction.isActive)
                transaction.rollback()
            throw e
        } finally {
            entityManager?.close()
        }

        return cards.map(Card::toModel).toList()
    }

    override fun getByParticipant(userId: Int): List<CardViewModel> {
        val cards: List<Card>
        var entityManager: EntityManager? = null
        var transaction: EntityTransaction? = null
        try {
            entityManager = entityManagerFactory
                    .createEntityManager()
            transaction = entityManager!!.transaction
            transaction!!.begin()

            cards = entityManager.createQuery(
                    "SELECT c FROM card c " +
                            "INNER JOIN c.participants as p " +
                            "WHERE p.userId = :userId", Card::class.java)
                    .setParameter("userId", userId)
                    .resultList

            transaction.commit()
        } catch (e: Throwable) {
            if (transaction != null && transaction.isActive)
                transaction.rollback()
            throw e
        } finally {
            entityManager?.close()
        }

        return cards.map(Card::toModel).toList()
    }
}
