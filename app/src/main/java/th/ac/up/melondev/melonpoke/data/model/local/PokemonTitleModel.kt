package th.ac.up.melondev.melonpoke.data.model.local

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PokemonTitleModel(
    var value: String? = null
): Parcelable