package de.amirrocker.flowplayground.data.repository.search.remote

import de.amirrocker.flowplayground.data.repository.search.remote.dto.WordInfoDto
import retrofit2.http.GET
import retrofit2.http.Path

interface DictionaryApi {

    @GET("/api/v2/entries/en/{word}")
    suspend fun getDictionary(@Path("word") word:String):List<WordInfoDto>

    companion object {
        const val BASE_URL = "http://api.dictionaryapi.dev"
    }

}