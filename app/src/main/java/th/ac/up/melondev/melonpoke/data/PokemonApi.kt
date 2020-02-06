package th.ac.up.melondev.melonpoke.data

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url
import th.ac.up.melondev.melonpoke.data.model.api.PokemonDetailModel
import th.ac.up.melondev.melonpoke.data.model.api.PokemonDrawerModel
import th.ac.up.melondev.melonpoke.data.model.api.PokemonTypeDetailModel
import java.net.URI

interface PokemonApi {

    @GET("pokemon")
    suspend fun getPokemonList() : Response<PokemonDrawerModel>

    @GET("pokemon")
    suspend fun getPokemonList(@Query("offset") offset :Int,@Query("limit") limit :Int) : Response<PokemonDrawerModel>

    @GET
    suspend fun getPokemonDetail(@Url url: URI) : Response<PokemonDetailModel>

    @GET("pokemon/{id}")
    suspend fun getPokemonDetail(@Path("id") id :Int) : Response<PokemonDetailModel>

    @GET("type/{name}")
    suspend fun getPokemonTypeDetail(@Path("name") name :String) : Response<PokemonTypeDetailModel>



}