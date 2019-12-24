package ru.leoltron.onmeeting.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import ru.leoltron.onmeeting.model.TagModel
import ru.leoltron.onmeeting.model.TagViewModel
import ru.leoltron.onmeeting.model.database.Tag
import ru.leoltron.onmeeting.repo.TagRepository
import ru.leoltron.onmeeting.util.toModel
import ru.leoltron.onmeeting.util.toViewModel
import ru.leoltron.onmeeting.util.updateFrom
import javax.validation.Valid

@RestController
@CrossOrigin(origins = ["*"], allowedHeaders = ["*"])
@RequestMapping("/api/tag")
class TagController : BaseController() {
    @Autowired
    lateinit var repository: TagRepository

    @ResponseBody
    @GetMapping("/getAll")
    fun getAll(): Iterable<TagViewModel> = repository.findAll().map(Tag::toViewModel)

    @ResponseBody
    @GetMapping("/get")
    fun get(@RequestParam(name = "id", required = true) id: Int): ResponseEntity<TagModel> {
        val tag = repository.findByIdOrNull(id) ?: return notFound()
        return ok(tag.toModel())
    }

    @PostMapping("/add")
    fun add(@Valid @RequestBody tagModel: TagModel): ResponseEntity<Any> {
        if (repository.findByName(tagModel.name).any()) {
            return badRequest("tag with ${tagModel.name} already exists")
        }

        val saved = repository.save(Tag(tagModel.name, tagModel.description, tagModel.color))
        return ok(saved.toViewModel())
    }

    @PatchMapping("/update")
    fun update(@RequestParam(name = "id", required = true) id: Int, @Validated @RequestBody tagModel: TagModel): ResponseEntity<Any> {
        val updatingTag = repository.findByIdOrNull(id) ?: return notFound("no tag with id $id found")

        updatingTag.updateFrom(tagModel)
        val saved = repository.save(updatingTag)

        return ok(saved.toViewModel())
    }

    @DeleteMapping("/delete")
    fun delete(@RequestParam(name = "id", required = true) id: Int): ResponseEntity<Any> {
        val deletingTag = repository.findByIdOrNull(id) ?: return notFound("no tag with id $id found")
        repository.delete(deletingTag)
        return ok()
    }
}
