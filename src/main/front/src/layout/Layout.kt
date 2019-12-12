package layout

import react.*
import react.dom.*
import react.router.dom.*

import components.auth.*
import ticker.*

var userLogin = false

class Layout : RComponent<RProps, RState>() {
    override fun RBuilder.render() {
        browserRouter {
            switch {
                if (!userLogin) {
                    redirect(from = "/board", to = "/")
                }
                redirect(from = "/", to = if (!userLogin) "/login" else "/board", exact = true)
                route("/login", exact = true) {
                    auth()
                }
                route("/board", strict = true) {
                    ticker()
                }
//                route("/logout")
            }
        }
    }
}

fun RBuilder.layout() = child(Layout::class) {}
