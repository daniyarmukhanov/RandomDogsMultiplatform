package kz.edu.sdu.dogs

import androidx.compose.runtime.mutableStateListOf
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable

class MainViewModel {

    val listOfDogsImageLink = mutableStateListOf<String>()
    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.Main)
    private val queries = getDb(DriverFactory()).dogsQueries


    init {
        showInitialDogs()
    }

    private fun showInitialDogs() {
        scope.launch {
            listOfDogsImageLink.clear()
            listOfDogsImageLink.addAll(
                queries.getAll().executeAsList().map { it.name }
            )
        }
    }

    fun showAnotherDog() {
        scope.launch {
            val dog = httpClient.get("https://dog.ceo/api/breeds/image/random").body<Dog>()
            queries.insertDog(dog.message)
            listOfDogsImageLink.add(dog.message)
        }
    }
}

@Serializable
data class Dog(
    val message: String,
    val status: String
)

val httpClient: HttpClient = HttpClient(OkHttp) {
    install(ContentNegotiation) {
        json()
    }
}



