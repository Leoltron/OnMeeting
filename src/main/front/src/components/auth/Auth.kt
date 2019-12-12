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
//import kotlin.browser.*

import features.http.client.*

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
            input(type = InputType.text, name = "login") {
                attrs {
                    placeholder = "Username"
                    value = state.login
                    onChangeFunction = ::onLoginChange
                }
            }
            input(type = InputType.password, name = "password") {
                attrs {
                    placeholder = "Password"
                    value = state.password
                    onChangeFunction = ::onPasswordChange
                }
            }
            div("Auth-controls") {
                button {
                    attrs {
                        onClickFunction = ::signUpp
                    }
                    +"Sign Up"
                }
                button {
                    attrs {
                        onClickFunction = ::signIn
                    }
                    +"Sign In"
                }
            }
        }
    }

    fun signIn(event: Event) {
        println(state.login + state.password)
    }

    fun signUpp(event: Event) {
        signUp(state.login, state.password)
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
