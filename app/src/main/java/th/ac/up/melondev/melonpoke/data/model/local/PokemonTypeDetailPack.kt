package th.ac.up.melondev.melonpoke.data.model.local

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import th.ac.up.melondev.melonpoke.data.model.api.PokemonTypeDetailModel

@Parcelize
data class PokemonTypeDetailPack(
    val type :PokemonTypeModel,
    val detail :PokemonTypeDetailModel
) : Parcelable