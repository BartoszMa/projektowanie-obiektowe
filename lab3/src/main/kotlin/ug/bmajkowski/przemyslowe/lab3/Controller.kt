package ug.bmajkowski.przemyslowe.lab3

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.encodeToString
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestHeader


@Serializable
data class SampleData(val sampleString: String)

@RestController
@RequestMapping("/api/v1")
class Controller @Autowired constructor(private val authorization: Authorization){

    private val sampleDataValues = listOf(
        SampleData("Laptop"),
        SampleData("Smartphone"),
        SampleData("Tablet")
    )

    @GetMapping("/test", produces = ["application/json"])
    fun getSample(@RequestHeader("Authorization", required = false) authHeader: String?): String {
        return if (authorization.authorize(authHeader)) {
            Json.encodeToString(sampleDataValues)
        } else {
            Json.encodeToString("Unauthorized")
        }
    }
}