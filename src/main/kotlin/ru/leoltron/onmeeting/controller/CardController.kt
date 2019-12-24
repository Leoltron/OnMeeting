package ru.leoltron.onmeeting.controller

import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import ru.leoltron.onmeeting.ICardProvider
import ru.leoltron.onmeeting.model.CardAddOrEditModel
import ru.leoltron.onmeeting.model.CardViewModel
import ru.leoltron.onmeeting.repo.CardRepository
import ru.leoltron.onmeeting.repo.TagRepository
import ru.leoltron.onmeeting.repo.UserRepository
import ru.leoltron.onmeeting.util.toCard
import ru.leoltron.onmeeting.util.toModel
import ru.leoltron.onmeeting.util.updateFromModel
import java.security.Principal

@RestController
@CrossOrigin(origins = ["*"], allowedHeaders = ["*"])
@RequestMapping("/api/card")
class CardController(
        private val cardProvider: ICardProvider,
        private val cardRepository: CardRepository,
        private val userRepository: UserRepository,
        private val tagRepository: TagRepository) : BaseController() {

    @GetMapping("/getParticipating")
    fun get(principal: Principal): ResponseEntity<List<CardViewModel>> {
        val user = userRepository.findByUsername(principal.name).firstOrNull() ?: return unauthorized()
        return ok(user.participatingCards.toList().sortedWith(compareBy { it.cardId }).map { it.toModel() }.toList())
    }


    @GetMapping("/getParticipatingByTag")
    fun getByTag(principal: Principal, @RequestParam(required = true) tagId: Int): ResponseEntity<List<CardViewModel>> {
        val userId = userRepository.findByUsername(principal.name).firstOrNull()?.userId ?: return unauthorized()
        return ok(cardProvider.getByTagAndParticipant(userId, tagId))
    }

    @PostMapping("/add")
    fun add(principal: Principal, @RequestBody cardAddOrEditModel: CardAddOrEditModel): ResponseEntity<Any> {
        val user = userRepository.findByUsername(principal.name).firstOrNull() ?: return unauthorized()
        val userId = user.userId
        val card = cardAddOrEditModel.toCard(userId)
        card.user = user
        card.participants.addAll(userRepository.findAllById(cardAddOrEditModel.participantsIds))
        card.tags.addAll(tagRepository.findAllById(cardAddOrEditModel.tagIds))
        val saved = cardRepository.save(card)
        return ok(saved.toModel())
    }

    @PatchMapping("/{cardId}/edit")
    fun getOwned(principal: Principal,
                 @RequestBody cardAddOrEditModel: CardAddOrEditModel,
                 @PathVariable cardId: Int): ResponseEntity<Any> {
        val user = userRepository.findByUsername(principal.name).firstOrNull() ?: return unauthorized()
        val userId = user.userId

        val card = cardRepository.findByIdOrNull(cardId) ?: return badRequest("card not found")
        if (card.userId != userId) return forbidden("you don't have an access to edit card with id $cardId")
        val participants = userRepository.findAllById(cardAddOrEditModel.participantsIds)
        val tags = tagRepository.findAllById(cardAddOrEditModel.tagIds)
        card.updateFromModel(cardAddOrEditModel, participants, tags)
        val saved = cardRepository.save(card)
        return ok(saved.toModel())
    }

    @DeleteMapping("/{cardId}/delete")
    fun delete(principal: Principal,
               @PathVariable cardId: Int): ResponseEntity<Any> {
        val userId = userRepository.findByUsername(principal.name).firstOrNull()?.userId ?: return unauthorized()
        val card = cardRepository.findByIdOrNull(cardId) ?: return badRequest("card with id $cardId not found")
        if (card.userId != userId) return forbidden("you don't have an access to delete card with id $cardId")
        cardRepository.deleteById(cardId)
        return ok()
    }

}