import io.restassured.RestAssured
import io.restassured.response.Response
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
@JsonIgnoreProperties(ignoreUnknown = true)
data class User(
    val id: Int,
    val name: String,
    val username: String,
    val email: String,
    val address: Address,
    val phone: String,
    val website: String
)



@JsonIgnoreProperties(ignoreUnknown = true)
data class Address(
    val street: String,
    val suite: String,
    val city: String,
    val zipcode: String,
    val geo: Geo
)
@JsonIgnoreProperties(ignoreUnknown = true)
data class Geo(
    val lat: String,
    val lng: String
)

fun main() {
    RestAssured.baseURI = "https://jsonplaceholder.typicode.com"

    val response: Response = RestAssured.given()
        .get("/users")
        .then()
        .statusCode(200)
        .extract()
        .response()

    val objectMapper = jacksonObjectMapper().apply {
        configure(com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
    }
    val users: List<User> = objectMapper.readValue(response.asString())

    val filteredUsers = users.filter { it.address.city == "Gwenborough" }

    println("Filtered Users:")
    filteredUsers.forEach {
        println("Name: ${it.name}, Email: ${it.email}, City: ${it.address.city}")
    }
}
