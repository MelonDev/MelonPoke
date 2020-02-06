package th.ac.up.melondev.melonpoke.data.model.local

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import th.ac.up.melondev.melonpoke.data.model.api.PokemonURIModel

@Parcelize
data class PokemonTypeListModel(
    val title :String? = null,
    val typeList: List<PokemonURIModel>? = null
) : Parcelable