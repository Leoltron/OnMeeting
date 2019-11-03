package ru.leoltron.onmeeting.controller

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import ru.leoltron.onmeeting.ICardProvider
import ru.leoltron.onmeeting.model.CardAddOrEditModel
import ru.leoltron.onmeeting.model.CardViewModel
import ru.leoltron.onmeeting.repo.CardRepository
import ru.leoltron.onmeeting.repo.TagRepository
import ru.leoltron.onmeeting.repo.UserRepository
import ru.leoltron.onmeeting.util.toCard
import java.security.Principal

@RestController
@RequestMapping("/api/card")
class CardController(
        private val cardProvider: ICardProvider,
        private val cardRepository: CardRepository,
        private val userRepository: UserRepository,
        private val tagRepository: TagRepository) : BaseController() {

    @GetMapping("/getParticipating")
    fun get(principal: Principal): ResponseEntity<List<CardViewModel>> {
        val userId = userRepository.findByUsername(principal.name).firstOrNull()?.userId ?: return unauthorized()
        return ok(cardProvider.getByParticipant(userId))
    }

    @GetMapping("/getParticipatingByTag")
    fun getByTag(principal: Principal, @RequestParam(required = true) tagId: Int): ResponseEntity<List<CardViewModel>> {
        val userId = userRepository.findByUsername(principal.name).firstOrNull()?.userId ?: return unauthorized()
        return ok(cardProvider.getByTagAndParticipant(userId, tagId))
    }

    @PostMapping("/add")
    fun add(principal: Principal, @RequestBody cardAddOrEditModel: CardAddOrEditModel): ResponseEntity<Any> {
        val userId = userRepository.findByUsername(principal.name).firstOrNull()?.userId ?: return unauthorized()

        val card = cardAddOrEditModel.toCard(userId)
        card.participants.addAll(userRepository.findAllById(cardAddOrEditModel.participantsIds))
        card.tags.addAll(tagRepository.findAllById(cardAddOrEditModel.tagIds))
        cardRepository.save(card)

        return ok()
    }

    @PatchMapping("/{cardId}/edit")
    fun getOwned(principal: Principal,
                 @RequestBody cardAddOrEditModel: CardAddOrEditModel,
                 @PathVariable cardId: Int): ResponseEntity<Any> {
        return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }

}