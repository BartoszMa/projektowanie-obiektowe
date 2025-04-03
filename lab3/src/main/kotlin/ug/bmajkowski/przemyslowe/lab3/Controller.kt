package ug.bmajkowski.przemyslowe.lab3

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.encodeToString
import org.springframework.web.bind.annotation.GetMapping


@Serializable
data class SampleData(val sampleString: String)

@RestController
@RequestMapping("/api/v1")
class Controller {

    private val sampleDataValues = listOf(
        SampleData("Laptop"),
        SampleData("Smartphone"),
        SampleData("Tablet")
    )

    @GetMapping("/test", produces = ["application/json"])
    fun getSample(): String {
        return Json.encodeToString(sampleDataValues)
    }
}