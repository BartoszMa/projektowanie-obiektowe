package ug.bmajkowski.przemyslowe.lab3

import org.springframework.context.annotation.Lazy
import org.springframework.stereotype.Component

@Component
@Lazy
class AuthorizationLazy {
    fun authorize(token: String?): Boolean {
        return token == "sample_token"
    }
}
