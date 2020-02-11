package th.ac.up.melondev.melonpoke.data.model.api

import android.os.Parcelable
import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
@Keep
data class PokemonTypeDetailModel(
    @SerializedName("damage_relations") var damageRelations: PokemonTypeDamageRelationsModel? = null

): Parcelable

@Parcelize
@Keep
data class PokemonTypeDamageRelationsModel(
    @SerializedName("double_damage_from") var doubleDamageFrom: List<PokemonURIResult>? = null,
    @SerializedName("double_damage_to") var doubleDamageTo: List<PokemonURIResult>? = null,
    @SerializedName("half_damage_from") var halfDamageFrom: List<PokemonURIResult>? = null,
    @SerializedName("half_damage_to") var halfDamageTo: List<PokemonURIResult>? = null,
    @SerializedName("no_damage_from") var noDamageFrom: List<PokemonURIResult>? = null,
    @SerializedName("no_damage_to") var noDamageTo: List<PokemonURIResult>? = null

    ): Parcelable