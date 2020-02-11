package th.ac.up.melondev.melonpoke.data.model.api

import android.os.Parcelable
import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import java.net.URI

@Parcelize
@Keep
data class PokemonDetailModelSprites(
    @SerializedName("back_default") var back_default: String? = null,
    @SerializedName("back_female") var back_female: String? = null,
    @SerializedName("back_shiny") var back_shiny: String? = null,
    @SerializedName("back_shiny_female") var back_shiny_female: String? = null,
    @SerializedName("front_default") var front_default: String? = null,
    @SerializedName("front_female") var front_female: String? = null,
    @SerializedName("front_shiny") var front_shiny: String? = null,
    @SerializedName("front_shiny_female") var front_shiny_female: String? = null
): Parcelable

@Parcelize
@Keep
data class PokemonDetailModel(
    @SerializedName("name") var name: String? = null,
    @SerializedName("weight") var weight: Int? = null,
    @SerializedName("height") var height: Int? = null,
    @SerializedName("id") var id: Int? = null,
    @SerializedName("base_experience") var base_experience: Int? = null,
    @SerializedName("is_default") var is_default: Boolean? = null,
    @SerializedName("location_area_encounters") var location_area_encounters: URI? = null,
    @SerializedName("order") var order: Int? = null,
    @SerializedName("species") var species: PokemonURIResult? = null,

    @SerializedName("game_indices") var game_indices: List<PokemonGameIndicesModel>? = null,

    @SerializedName("stats") var stats: List<PokemonStatsModel>? = null,
    @SerializedName("types") var types: List<PokemonTypeSlotModel>? = null,

    @SerializedName("sprites") var sprites: PokemonDetailModelSprites? = null

    //@SerializedName("moves") var moves: List<PokemonMoveModel>? = null,
    //@SerializedName("held_items") var held_items: List<PokemonHeldItemsModel>? = null
    //@SerializedName("abilities") var abilities: List<PokemonAbilitiesModel>? = null,
    //@SerializedName("forms") var forms: List<PokemonURIModel>? = null,

): Parcelable

@Parcelize
@Keep
data class PokemonTypeSlotModel(
    @SerializedName("slot") var slot: Int? = null,
    @SerializedName("type") var type: PokemonURIResult? = null
): Parcelable

@Parcelize
@Keep
data class PokemonStatsModel(
    @SerializedName("base_stat") var base_stat: Int? = null,
    @SerializedName("effort") var effort: Int? = null,
    @SerializedName("stat") var stat: PokemonURIResult? = null
): Parcelable

@Parcelize
@Keep
data class PokemonHeldItemsModel(
    @SerializedName("item") var stat: PokemonURIResult? = null,
    @SerializedName("version_details") var version_details: List<PokemonVersionDetailModel>? = null

): Parcelable

@Parcelize
@Keep
data class PokemonVersionDetailModel(
    @SerializedName("rarity") var rarity: Int? = null,
    @SerializedName("version") var version: PokemonURIResult? = null
): Parcelable

@Parcelize
@Keep
data class PokemonGameIndicesModel(
    @SerializedName("game_index") var game_index: Int? = null,
    @SerializedName("version") var version: PokemonURIResult? = null

): Parcelable

@Parcelize
@Keep
data class PokemonAbilitiesModel(
    @SerializedName("is_hidden") var is_hidden: Boolean? = null,
    @SerializedName("slot") var slot: Int? = null,
    @SerializedName("ability") var ability: PokemonURIResult? = null

): Parcelable

@Parcelize
@Keep
data class PokemonMoveModel(
    @SerializedName("move") var move: PokemonURIResult? = null,
    @SerializedName("version_group_details") var version_group_details: List<PokemonVersionGroupDetailModel>? = null

): Parcelable

@Parcelize
@Keep
data class PokemonVersionGroupDetailModel(
    @SerializedName("level_learned_at") var level_learned_at: Int? = null,
    @SerializedName("move_learn_method") var move_learn_method: PokemonURIResult? = null
): Parcelable




