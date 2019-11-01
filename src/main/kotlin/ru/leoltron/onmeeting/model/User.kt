package ru.leoltron.onmeeting.model

import ru.leoltron.onmeeting.validation.StringLength
import javax.persistence.*


@Entity(name = "user_data")
class User(
        @StringLength(min = 5, max = 50) var username: String,
        @StringLength(max = 255) var hash: String,
        @Column(name = "user_id") @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        val userId: Int = -1) {

    @OneToMany(mappedBy = "user", fetch=FetchType.EAGER)
    val authorities: Set<UserAuthority>? = null

    /*
    @ManyToMany(cascade = [CascadeType.ALL])
    @JoinTable(name = "Employee_Project", joinColumns = [JoinColumn(name = "employee_id")], inverseJoinColumns = [JoinColumn(name = "project_id")])
    var projects: Set<Project> = HashSet<Project>()*/


    private constructor() : this("", "")
}