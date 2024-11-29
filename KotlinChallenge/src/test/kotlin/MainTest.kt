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

        assertEquals(10, users.size, "La API debería devolver 10 usuarios")
    }


    @Test
    fun `filter users by city`() {
        // Configurar la URL base
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com"

        // Realizar la solicitud GET
        val response: Response = RestAssured.given()
            .get("/users")
            .then()
            .statusCode(200) // Asegurarse de que la respuesta sea 200 OK
            .extract()
            .response()

        // Deserializar el JSON en una lista de usuarios
        val objectMapper = jacksonObjectMapper()
        val users: List<User> = objectMapper.readValue(response.asString())

        // Filtrar usuarios cuya ciudad sea "Gwenborough"
        val filteredUsers = users.filter { it.address.city == "Gwenborough" }

        // Validar los resultados
        assertEquals(1, filteredUsers.size, "Debe haber un usuario en Gwenborough")
        assertEquals("Leanne Graham", filteredUsers[0].name)
    }
}
