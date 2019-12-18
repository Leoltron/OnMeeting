package ru.leoltron.onmeeting.model.database

import ru.leoltron.onmeeting.validation.StringLength
import java.sql.Timestamp
import java.util.*
import javax.persistence.*

@Entity(name = "card")
class Card(
        @StringLength(min = 1, max = 50) var title: String,
        @Column(name = "user_id") var userId: Int,
        @StringLength(max = 50) var locationString: String? = null,
        @Column(name = "start_date") var startDate: Timestamp? = null,
        @Column(name = "end_date") var endDate: Timestamp? = null,
        @Column(name = "card_id") @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        val cardId: Int = -1) {

    @ManyToMany(cascade = [CascadeType.MERGE, CascadeType.PERSIST], fetch = FetchType.EAGER)
    @JoinTable(
            name = "card_tag",
            joinColumns = [JoinColumn(name = "card_id")],
            inverseJoinColumns = [JoinColumn(name = "tag_id")])
    var tags: MutableSet<Tag> = HashSet()

    @ManyToMany(cascade = [CascadeType.MERGE, CascadeType.PERSIST], fetch = FetchType.EAGER)
    @JoinTable(
            name = "card_participant",
            joinColumns = [JoinColumn(name = "card_id")],
            inverseJoinColumns = [JoinColumn(name = "user_id")])
    var participants: MutableSet<User> = HashSet()

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false, updatable = false, insertable = false)
    var user: User? = null

    @Suppress("unused")
    private constructor() : this("", 0)
}
