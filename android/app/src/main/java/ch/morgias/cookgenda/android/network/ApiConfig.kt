package ch.morgias.cookgenda.android.network

import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory

private const val BASE_URL = "localhost:8080"
private const val BASE_API = "$BASE_URL/api/v1"


val retrofit: Retrofit = Retrofit.Builder()
    .addConverterFactory(ScalarsConverterFactory.create())
    .baseUrl(BASE_API)
    .build()








