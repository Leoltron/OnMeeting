package ru.leoltron.onmeeting.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.view.RedirectView

@RestController
class RedirectController {
    @GetMapping("/login", "/board")
    fun redirectToHome(): RedirectView = RedirectView("")
}