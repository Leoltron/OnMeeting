package ru.leoltron.onmeeting

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController


data class HelloResponse(val message: String);

@RestController
class HelloController {
    @RequestMapping("/hello")
    fun hello(@RequestParam(value = "name", defaultValue = "World") name: String): HelloResponse {
        return HelloResponse("Hello, " + name);
    }
}
