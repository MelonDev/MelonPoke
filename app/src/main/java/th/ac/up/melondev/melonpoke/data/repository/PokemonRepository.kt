package th.ac.up.melondev.melonpoke.data.repository

import th.ac.up.melondev.melonpoke.data.PokeApi
import th.ac.up.melondev.melonpoke.data.RetrofitClient

class PokemonRepository {
    private var service: PokeApi = RetrofitClient().createService(PokeApi::class.java)

    suspend fun getPokemonList() = service.getPokemonList()
}