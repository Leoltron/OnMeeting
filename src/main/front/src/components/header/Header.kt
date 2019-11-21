package components.header

import react.*
import react.dom.*

class Header : RComponent<RProps, RState>() {
    override fun RBuilder.render() {
        div("Header") {
            h1 {
                +"OnMeeting"
            }
        }
    }
}

fun RBuilder.header() = child(Header::class) {}
