package ru.leoltron.onmeeting.model.database

import ru.leoltron.onmeeting.validation.StringLength
import javax.persistence.*


@Entity(name = "user_data")
class User(
        @StringLength(min = 5, max = 50) var username: String,
        @StringLength(max = 255) var hash: String,
        @Column(name = "user_id") @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        val userId: Int = -1) {

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    var authorities: Set<UserAuthority> = HashSet()

    @OneToMany(mappedBy = "user")
    var cards: Set<Card> = HashSet()

    @ManyToMany(mappedBy = "participants")
    var participatingCards: Set<Card> = HashSet()

    constructor() : this("", "")
}