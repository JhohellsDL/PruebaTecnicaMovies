package com.jdlstudios.peliculasapp.data.api

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
//6e63c2317fbe963d76c3bdc2b785f6d1
//f46b58478f489737ad5a4651a4b25079
const val API_KEY = "7a7195343612cd8d3fa5690769349313"
const val BASE_URL = "https://api.themoviedb.org/3/"

const val POSTER_BASE_URL = "https://image.tmdb.org/t/p/w500"

const val FIRST_PAGE = 1
const val POST_PER_PAGE = 20

// https://api.themoviedb.org/3/movie/popular?api_key=f46b58478f489737ad5a4651a4b25079&page=1
// https://api.themoviedb.org/3/movie/299534?api_key=f46b58478f489737ad5a4651a4b25079
// https://image.tmdb.org/t/p/w500/8UlWHLMpgZm9bx6QYh0NFoq67TZ.jpg
// https://image.tmdb.org/t/p/w500/srYya1ZlI97Au4jUYAktDe3avyA.jpg

object TheMovieDBClient {

    fun getClient(): TheMovieDBInterface {

        val requestInterceptor = Interceptor { chain ->


            val url = chain.request()
                .url()
                .newBuilder()
                .addQueryParameter("api_key", API_KEY)
                .build()

            val request = chain.request()
                .newBuilder()
                .url(url)
                .build()

            return@Interceptor chain.proceed(request)
        }

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(requestInterceptor)
            .connectTimeout(60, TimeUnit.SECONDS)
            .build()

        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(TheMovieDBInterface::class.java)

    }
}