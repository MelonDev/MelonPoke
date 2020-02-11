package th.ac.up.melondev.melonpoke.data.model.local

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PokemonSmallTypeModel(
    val name: String? = null,
    val image: Int? = null,
    val background: Int? = null
) : Parcelable