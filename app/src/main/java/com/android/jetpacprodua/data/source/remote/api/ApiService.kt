package com.android.jetpacprodua.data.source.remote.api


import com.android.jetpacprodua.data.source.remote.response.MovieRemoteResponse
import com.android.jetpacprodua.data.source.remote.response.TvRemoteResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("3/movie/popular")
    suspend fun getMovieKoreaPopular(
        @Query("api_key")
        api_key: String
    ): Response<MovieRemoteResponse>

    @GET("3/movie/{movie_id}")
   suspend fun getMovieKoreaDetail(
        @Path("movie_id")
        movie_id: Int,
        @Query("api_key")
        api_key: String
    ): Response<MovieRemoteResponse>

    @GET("3/tv/popular")
   suspend fun getTvKoreaPopular(
        @Query("api_key")
        api_key: String
    ): Response<TvRemoteResponse>

    @GET("3/tv/{id}")
  suspend  fun getTvKoreaDetail(
        @Path("id")
        id: Int,
        @Query("api_key")
        api_key: String
    ): Response<TvRemoteResponse>
}