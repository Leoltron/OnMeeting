package layout

import react.*
import react.dom.*
import components.auth.*

class Layout : RComponent<RProps, RState>() {
    override fun RBuilder.render() {
        auth()
    }
}

fun RBuilder.layout() = child(Layout::class) {}
