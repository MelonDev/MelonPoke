package th.ac.up.melondev.melonpoke.data.model.local

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import th.ac.up.melondev.melonpoke.data.model.api.PokemonURIResult
import th.ac.up.melondev.melonpoke.utill.PokemonTypeListener

@Parcelize
data class PokemonSmallTypeListModel(
    val title: String? = null,
    val typeList: List<PokemonURIResult>? = null,
    val listenerPokemon: PokemonTypeListener? = null
) : Parcelable