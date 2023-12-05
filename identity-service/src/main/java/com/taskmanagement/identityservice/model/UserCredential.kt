package com.taskmanagement.identityservice.model

import jakarta.persistence.*
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

@Entity
@Table(name = "USER_CREDENTIALS")
data class UserCredential @JvmOverloads constructor(

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: String? = "",
    val email: String,
    val role: Role? = Role.ROLE_USER,
    private val password: String

) : UserDetails {
    fun getClaims(): Map<String, Any> {
        val claims = HashMap<String, Any>()
        claims[UserClaims.USER_ID.value] = this.id ?: ""
        claims[UserClaims.EMAIL.value] = this.email
        claims[UserClaims.ROLE.value] = this.role ?: Role.ROLE_USER
        return claims
    }

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return listOf(SimpleGrantedAuthority(role?.name)).toMutableList()
    }

    override fun getPassword(): String {
        return this.password
    }

    override fun getUsername(): String {
        return this.email
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return true
    }
}
