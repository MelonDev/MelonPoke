package th.ac.up.melondev.melonpoke.data

import retrofit2.Response
import retrofit2.http.GET
import th.ac.up.melondev.melonpoke.data.model.PokemonDrawerModel

interface PokeApi {

    @GET("/api/v2/pokemon/")
    suspend fun getPokemonList() : Response<PokemonDrawerModel>



}