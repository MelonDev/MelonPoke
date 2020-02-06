package th.ac.up.melondev.melonpoke.data.model.local

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PokemonInformationModel(
    var name: String? = null,
    var weight: Int? = null,
    var height: Int? = null,
    var base_experience: Int? = null
): Parcelable