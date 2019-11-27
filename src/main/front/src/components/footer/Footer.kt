package components.footer

import react.*
import react.dom.*

class Footer : RComponent<RProps, RState>() {
    override fun RBuilder.render() {
        div("Footer") {
            p {
                +"designed with <3"
            }
        }
    }
}

fun RBuilder.footer() = child(Footer::class) {}
