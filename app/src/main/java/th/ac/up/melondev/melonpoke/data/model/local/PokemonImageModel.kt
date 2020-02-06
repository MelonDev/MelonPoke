package th.ac.up.melondev.melonpoke.data.model.local

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PokemonImageModel(
    var value: String? = null,
    var pokemonType: PokemonTypeModel? = null
) : Parcelable
