package th.ac.up.melondev.melonpoke.data.repository

import th.ac.up.melondev.melonpoke.data.PokemonApi
import th.ac.up.melondev.melonpoke.data.RetrofitClient
import java.net.URI

class PokemonRepository {
    private var service: PokemonApi = RetrofitClient.createService(PokemonApi::class.java)

    suspend fun getPokemonList() = service.getPokemonList()

    suspend fun getPokemonList(offset :Int,limit :Int) = service.getPokemonList(offset,limit)

    suspend fun getPokemonDetail(uri :URI) = service.getPokemonDetail(uri)

    suspend fun getPokemonDetail(id :Int) = service.getPokemonDetail(id)

    suspend fun getPokemonTypeDetail(name :String) = service.getPokemonTypeDetail(name)

}