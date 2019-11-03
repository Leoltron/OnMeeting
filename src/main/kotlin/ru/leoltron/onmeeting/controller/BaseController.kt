package ru.leoltron.onmeeting.controller

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

abstract class BaseController {
    protected fun <T> notFound(body: T? = null) = ResponseEntity(body, HttpStatus.NOT_FOUND)
    protected fun <T> badRequest(body: T? = null) = ResponseEntity(body, HttpStatus.BAD_REQUEST)
    protected fun <T> unauthorized(body: T? = null) = ResponseEntity(body, HttpStatus.UNAUTHORIZED)
    protected fun <T> ok(body: T? = null) = ResponseEntity(body, HttpStatus.OK)
}
