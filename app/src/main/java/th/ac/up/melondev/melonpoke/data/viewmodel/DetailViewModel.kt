package th.ac.up.melondev.melonpoke.data.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response
import th.ac.up.melondev.melonpoke.data.model.api.PokemonDetailModel
import th.ac.up.melondev.melonpoke.data.model.local.PokemonTypeDetailPack
import th.ac.up.melondev.melonpoke.data.model.local.PokemonTypeModel
import th.ac.up.melondev.melonpoke.data.repository.PokemonRepository
import th.ac.up.melondev.melonpoke.utill.NetworkResponse

class DetailViewModel : ViewModel() {

    private val repository: PokemonRepository = PokemonRepository()

    fun loadPokemonDetail(id: Int) = liveData(Dispatchers.IO) {

        emit(NetworkResponse.loading())
        val response: Response<PokemonDetailModel> = repository.getPokemonDetail(id)
        if (response.isSuccessful) {
            emit(NetworkResponse.success(response.body()))
        } else {
            emit(NetworkResponse.error(response.errorBody().toString()))
        }
    }


    fun loadPokemonTypeDetail(type :PokemonTypeModel?): LiveData<NetworkResponse<PokemonTypeDetailPack>> {

        return liveData(Dispatchers.IO) {
            emit(NetworkResponse.loading())
            type?.name?.let {name ->
                val detail = repository.getPokemonTypeDetail(name).body()
                detail?.let {
                    emit(NetworkResponse.success(PokemonTypeDetailPack(type,detail)))
                }
            }
        }
    }


    fun loadPokemonTypeDetailFromList(typeList :ArrayList<String>?) = liveData(Dispatchers.IO) {
        emit(NetworkResponse.loading())
        //val response: Response<PokemonDrawerModel> = repository.getPokemonList(offset,limit)

        val pokemonTypeArr = typeList ?: ArrayList()

        val pokemonTypeList = pokemonTypeArr.mapNotNull {
            it.let {
                repository.getPokemonTypeDetail(it).body()
            }
        }
        emit(NetworkResponse.success(pokemonTypeList))
    }

    private fun loadDetailData(id: Int, callback: PokemonDetailCallback) {
        CoroutineScope(Dispatchers.IO).launch {
            withContext(Dispatchers.Main) {
                val repository: PokemonRepository = PokemonRepository()
                val response: Response<PokemonDetailModel> = repository.getPokemonDetail(id)

                callback.value = response.body()
            }
        }
    }

    interface PokemonDetailCallback {
        var value: PokemonDetailModel?
    }
}