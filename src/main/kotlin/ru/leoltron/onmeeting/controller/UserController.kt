package ru.leoltron.onmeeting.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController
import ru.leoltron.onmeeting.model.TagModel
import ru.leoltron.onmeeting.model.UserModel
import ru.leoltron.onmeeting.model.database.Tag
import ru.leoltron.onmeeting.model.database.User
import ru.leoltron.onmeeting.repo.TagRepository
import ru.leoltron.onmeeting.repo.UserRepository
import ru.leoltron.onmeeting.util.toModel

@RestController
@RequestMapping("api/user")
internal class UserController : BaseController() {
    @Autowired
    lateinit var repository: UserRepository
    @ResponseBody
    @GetMapping("/getAll")
    fun getAll(): Iterable<UserModel> = repository.findAll().map(User::toModel)
}
