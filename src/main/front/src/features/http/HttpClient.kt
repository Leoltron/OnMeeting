package features.http.client

//import kotlinx.html.js.*
//import org.w3c.dom.url.*
import org.w3c.fetch.*
import kotlin.browser.*

import features.http.config.*


suspend fun signUp(username: String, password: String) {
    return window.fetch("${BASE_URL}/register?username=${username}&password=${password}",
            RequestInit(method = "POST", credentials = "same-origin".asDynamic())).await()
}

suspend fun signIn(username: String, password: String) {
    return window.fetch("${BASE_URL}/login?username=${username}&password=${password}",
            RequestInit(method = "POST", credentials = "same-origin".asDynamic())).await()
}

suspend fun getPrincipal() {
    return window.fetch("${BASE_URL}/principal",
            RequestInit(method = "GET", credentials = "same-origin".asDynamic())).await()
}

suspend fun getParticipatingCards() {
    return window.fetch("${BASE_URL}/api/card/getParticipating",
            RequestInit(method = "GET", credentials = "same-origin".asDynamic())).await()
}

suspend fun addCard(card: Card) {
    return window.fetch("${BASE_URL}/api/card/add",
            RequestInit(method = "POST", credentials = "same-origin".asDynamic())).await()
}

suspend fun editCard(card: Card, id: Int) {
    return window.fetch("${BASE_URL}/api/card/${id}/edit",
            RequestInit(method = "PATCH", credentials = "same-origin".asDynamic())).await()
}

suspend fun deleteCard(id: Int) {
    return window.fetch("${BASE_URL}/api/card/${id}/delete",
            RequestInit(method = "DELETE", credentials = "same-origin".asDynamic())).await()
}

suspend fun getAllUsers() {
    return window.fetch("${BASE_URL}/api/user/getAll",
            RequestInit(method = "GET", credentials = "same-origin".asDynamic())).await()
}

suspend fun getAllTags() {
    return window.fetch("${BASE_URL}/api/tag/getAll",
            RequestInit(method = "GET", credentials = "same-origin".asDynamic())).await()
}

suspend fun addTag(tag: Tag) {
    return window.fetch("${BASE_URL}/api/tag/add",
            RequestInit(method = "POST", credentials = "same-origin".asDynamic())).await()
}

suspend fun editTag(tag: Tag, id: Int) {
    return window.fetch("${BASE_URL}/api/tag/edit?id=${id}",
            RequestInit(method = "PATCH", credentials = "same-origin".asDynamic())).await()
}

suspend fun deleteTag(id: Int) {
    return window.fetch("${BASE_URL}/api/tag/delete?id=${id}",
            RequestInit(method = "DELETE", credentials = "same-origin".asDynamic())).await()
}



