package th.ac.up.melondev.melonpoke.utill

import android.os.Parcelable
import th.ac.up.melondev.melonpoke.data.model.local.PokemonTypeModel

interface PokemonTypeListener  : Parcelable {
    fun onClick(model : PokemonTypeModel)
}