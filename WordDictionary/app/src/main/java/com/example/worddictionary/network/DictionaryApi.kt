package com.example.worddictionary.network

import org.json.JSONArray
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

private const val API_KEY =  "f66678df-7c1b-4077-b809-68dc210f1fc1"
private const val BASE_URL =
    "https://www.dictionaryapi.com/api/v3/references/collegiate/json/"

private val retrofit = Retrofit.Builder()
    .addConverterFactory(ScalarsConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

interface DictionaryApiService {
    @GET("{word}?key=${API_KEY}")
    suspend fun getWord(@Path("word") type: String): Response<String>
}

object DictionaryApi {
    val retrofitService : DictionaryApiService by lazy {
        retrofit.create(DictionaryApiService::class.java) }
}


