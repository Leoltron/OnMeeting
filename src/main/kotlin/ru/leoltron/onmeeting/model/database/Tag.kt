package ru.leoltron.onmeeting.model.database

import ru.leoltron.onmeeting.validation.Color
import ru.leoltron.onmeeting.validation.StringLength
import java.util.*
import javax.persistence.*

@Entity(name = "tag")
class Tag(
        @StringLength(min = 1, max = 50) var name: String,
        @StringLength(max = 255) var description: String?,
        @Color var color: String,
        @Column(name = "tag_id") @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        val tagId: Int = -1) {

    @ManyToMany(mappedBy = "tags")
    var cards: Set<Card> = HashSet()

    constructor() : this("", "", "000000")
}