package components.auth

import react.*
import react.dom.*
import kotlinx.html.js.*
import kotlinx.html.InputType
import kotlinx.html.FormMethod
import org.w3c.dom.HTMLInputElement
import org.w3c.dom.events.Event
import org.w3c.dom.url.*
import org.w3c.fetch.*
import kotlin.browser.*

interface AuthState : RState {
    var login: String
    var password: String
}

class Auth : RComponent<RProps, AuthState>() {
    override fun AuthState.init() {
        login = ""
        password = ""
    }

    override fun RBuilder.render() {
        div("App-auth") {
            label {
                attrs["htmlFor"] = "a"
                +"Логин"
            }
            input(type = InputType.text, name = "login") {
                attrs {
                    placeholder = "Введите логин"
                    value = state.login
                    onChangeFunction = ::onLoginChange
                }
                attrs["id"] = "login"
            }
            label {
                attrs["htmlFor"] = "password"
                +"Пароль"
            }
            input(type = InputType.password, name = "password") {
                attrs {
                    placeholder = "Введите пароль"
                    value = state.password
                    onChangeFunction = ::onPasswordChange
                }
                attrs["id"] = "password"
            }
            button {
                attrs {
                    onClickFunction = ::signUp
                }
                +"Зарегестрироваться"
            }
            button {
                attrs {
                    onClickFunction = ::signIn
                }
                +"Войти"
            }
        }
    }

    fun signIn(event: Event) {
        println(state.login + state.password)
    }

    fun signUp(event: Event) {
        println(state.login + state.password)
        val BASE_URL: String = "http://localhost:8080"
        window.fetch("${BASE_URL}/register?username=${state.login}&password=${state.password}",
                RequestInit(method = "POST", credentials = "same-origin".asDynamic()))
    }

    fun onLoginChange(event: Event) {
        val target = event.target as HTMLInputElement
        setState {
            login = target.value
        }
    }

    fun onPasswordChange(event: Event) {
        val target = event.target as HTMLInputElement
        setState {
            password = target.value
        }
    }
}

fun RBuilder.auth() = child(Auth::class) {}
