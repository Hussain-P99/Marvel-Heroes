/**
 * Created by Hussain on 9/17/2022
 *
 */

package com.hsn.marvelheroes.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitBuilder {

    private val BASE_URL = "https://gateway.marvel.com/"

    private fun createRetrofit(): Retrofit {
        return Retrofit
            .Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(createOkhttpClient())
            .build()
    }

    private fun createOkhttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(3,TimeUnit.MINUTES)
            .readTimeout(4,TimeUnit.MINUTES)
            .writeTimeout(4,TimeUnit.MINUTES)
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()
    }

    val marvelsApi : MarvelApi by lazy {
        createRetrofit().create(MarvelApi::class.java)
    }
}