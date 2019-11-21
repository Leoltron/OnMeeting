package layout

import react.*
import react.dom.*
import logo.*
import ticker.*

class Layout : RComponent<RProps, RState>() {
    override fun RBuilder.render() {
        div("App-auth") {
            label {
                attrs["htmlFor"] = "login"
                +"Логин"
            }
            input {
                attrs["id"] = "login"
                attrs["placeholder"] = "Введите логин"
            }
            label {
                attrs["htmlFor"] = "password"
                +"Пароль"
            }
            input {
                attrs["id"] = "password"
                attrs["placeholder"] = "Введите пароль"
            }
        }
//        div("App-header") {
//            logo()
//            h2 {
//                +"Welcome to React with Kotlin"
//            }
//        }
//        p("App-intro") {
//            +"To get started, edit "
//            code { +"app/App.kt" }
//            +" and save to reload."
//        }
//        p("App-ticker") {
//            ticker()
//        }
    }
}

fun RBuilder.layout() = child(Layout::class) {}
