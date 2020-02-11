package th.ac.up.melondev.melonpoke.data.viewmodel

import androidx.lifecycle.*
import kotlinx.coroutines.*
import retrofit2.Response
import th.ac.up.melondev.melonpoke.data.model.api.PokemonDrawerModel
import th.ac.up.melondev.melonpoke.data.repository.PokemonRepository
import th.ac.up.melondev.melonpoke.utill.NetworkResponse


class HomeViewModel : ViewModel() {

    private val repository: PokemonRepository = PokemonRepository()

    fun loadData() = liveData(Dispatchers.IO) {
        emit(NetworkResponse.loading())
        val response: Response<PokemonDrawerModel> = repository.getPokemonList()
        if (response.isSuccessful) {
            emit(NetworkResponse.success(response.body()))
        } else {
            emit(NetworkResponse.error(response.errorBody().toString()))
        }
    }

    fun loadData(offset: Int, limit: Int) = liveData(Dispatchers.IO) {
        emit(NetworkResponse.loading())
        val response: Response<PokemonDrawerModel> = repository.getPokemonList(offset, limit)
        if (response.isSuccessful) {
            emit(NetworkResponse.success(response.body()))
        } else {
            emit(NetworkResponse.error(response.errorBody().toString()))
        }
    }

    fun loadPokemonAll(offset: Int = 0,limit: Int) = liveData(Dispatchers.IO) {
        emit(NetworkResponse.loading())
        val response: Response<PokemonDrawerModel> = repository.getPokemonList(offset,limit)

        val pokemonDrawerModelList = response.body()?.results ?: ArrayList()

        val pokemonList = pokemonDrawerModelList.mapNotNull {
            it.url?.let {
                repository.getPokemonDetail(it).body()
            }
        }
        emit(NetworkResponse.success(pokemonList))
    }




}
