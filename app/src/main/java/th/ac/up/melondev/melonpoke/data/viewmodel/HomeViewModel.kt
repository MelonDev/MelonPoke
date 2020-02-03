package th.ac.up.melondev.melonpoke.data.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import kotlinx.coroutines.Dispatchers
import retrofit2.Response
import th.ac.up.melondev.melonpoke.data.model.PokemonDrawerModel
import th.ac.up.melondev.melonpoke.data.repository.PokemonRepository
import th.ac.up.melondev.melonpoke.utill.NetworkResponse


class HomeViewModel : ViewModel() {

    private val repository :PokemonRepository = PokemonRepository()

    fun loadData() = liveData(Dispatchers.IO) {
        emit(NetworkResponse.loading())
        val response:Response<PokemonDrawerModel> = repository.getPokemonList()
        if (response.isSuccessful) {
            emit(NetworkResponse.success(response.body()))
        }else {
            emit(NetworkResponse.error(response.errorBody().toString()))
        }
    }


}
