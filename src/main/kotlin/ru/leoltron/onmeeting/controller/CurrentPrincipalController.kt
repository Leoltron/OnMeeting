package ru.leoltron.onmeeting.controller

import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.leoltron.onmeeting.model.PrincipalData
import java.security.Principal

@RestController
@CrossOrigin(origins = ["*"], allowedHeaders = ["*"])
@RequestMapping("/principal")
class CurrentPrincipalController : BaseController() {
    @GetMapping
    fun retrievePrincipal(principal: Principal?): PrincipalData {
        if (principal == null)
            return PrincipalData(false, "guest")
        return PrincipalData(true, principal.name)
    }
}
