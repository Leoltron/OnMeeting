package ru.leoltron.onmeeting.model

import ru.leoltron.onmeeting.validation.StringLength
import javax.persistence.*


@Entity(name = "user_authority")
class UserAuthority(
        @StringLength(max = 255) var authority: String,
        @Column(name = "user_id") var userId: Int,
        @Column(name = "user_authority_id")
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        val userAuthorityId: Int = -1) {

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false, updatable = false, insertable = false)
    private val user: User? = null

    /*
    @ManyToMany(cascade = [CascadeType.ALL])
    @JoinTable(name = "Employee_Project", joinColumns = [JoinColumn(name = "employee_id")], inverseJoinColumns = [JoinColumn(name = "project_id")])
    var projects: Set<Project> = HashSet<Project>()*/


    private constructor() : this("", 0)
}