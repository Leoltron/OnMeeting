package features.app

import react.*
import react.dom.*

import components.header.*
import components.footer.*
import layout.*

class App : RComponent<RProps, RState>() {
    override fun RBuilder.render() {
        div("App-root") {
            header()
            section("App-main") {
                layout()
            }
            footer()
        }
    }
}

fun RBuilder.app() = child(App::class) {}
