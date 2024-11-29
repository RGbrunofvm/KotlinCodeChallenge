import io.restassured.RestAssured
import io.restassured.response.Response
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class MainTest {

    @Test
    fun `fetch users successfully`() {
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

        assertEquals(10, users.size, "The api must return 10 users")
    }


    @Test
    fun `filter users by city`() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com"


        val response: Response = RestAssured.given()
            .get("/users")
            .then()
            .statusCode(200)
            .extract()
            .response()


        val objectMapper = jacksonObjectMapper()
        val users: List<User> = objectMapper.readValue(response.asString())

        val filteredUsers = users.filter { it.address.city == "Gwenborough" }

        assertEquals(1, filteredUsers.size, "Debe haber un usuario en Gwenborough")
        assertEquals("Leanne Graham", filteredUsers[0].name)
    }
}
