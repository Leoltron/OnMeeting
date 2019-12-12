package components.footer

import react.*
import react.dom.*

class Footer : RComponent<RProps, RState>() {
    override fun RBuilder.render() {
        div("Footer") {
            p {
                +"designed with fun"
            }
        }
    }
}

fun RBuilder.footer() = child(Footer::class) {}
