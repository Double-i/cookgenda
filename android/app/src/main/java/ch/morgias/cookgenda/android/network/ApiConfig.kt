package ch.morgias.cookgenda.android.network

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

private const val BASE_URL = "http://10.0.2.2:8080"
//private const val BASE_URL = "http://192.168.1.138:8080"
private const val BASE_API = "$BASE_URL/api/v1/"


val retrofit: Retrofit = Retrofit.Builder()
    .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
    .baseUrl(BASE_API)
    .build()









