package features.http.client

//import kotlinx.html.js.*
//import org.w3c.dom.url.*
import org.w3c.fetch.*
import kotlin.browser.*

import features.http.config.*


fun signUp(username: String, password: String) {
    window.fetch("${BASE_URL}/register?username=${username}&password=${password}",
            RequestInit(method = "POST", credentials = "same-origin".asDynamic()))
}

fun signIn(username: String, password: String) {

}
