package br.eng.pedro_mendes.github_followers.data.provider

import br.eng.pedro_mendes.github_followers.data.models.User
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


interface ApiService {
    @GET("users/pedromneto97/followers")
    fun getFollowers(): Call<List<User>>

    companion object {
        fun create(baseUrl: String): Retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}