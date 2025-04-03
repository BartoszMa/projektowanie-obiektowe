package ug.bmajkowski.przemyslowe.lab3

import org.springframework.stereotype.Component

@Component
class Authorization {
    fun authorize(token: String?): Boolean {
        return token == "sample_token"
    }
}
