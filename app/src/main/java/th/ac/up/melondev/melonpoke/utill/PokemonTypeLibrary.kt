package th.ac.up.melondev.melonpoke.utill

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.Log
import androidx.core.content.ContextCompat
import th.ac.up.melondev.melonpoke.R
import th.ac.up.melondev.melonpoke.data.model.api.PokemonTypeSlotModel
import th.ac.up.melondev.melonpoke.data.model.local.PokemonTypeModel

class PokemonTypeLibrary(private val context: Context) {

    private var data: ArrayList<PokemonTypeModel?> = ArrayList()

    init {
        data.apply {
            add(dataToPokemonType("normal", R.drawable.normal, R.drawable.type_background_normal))
            add(dataToPokemonType("fighting", R.drawable.fighting, R.drawable.type_background_fighting))
            add(dataToPokemonType("flying", R.drawable.flying, R.drawable.type_background_flying))
            add(dataToPokemonType("poison", R.drawable.poison, R.drawable.type_background_poison))
            add(dataToPokemonType("ground", R.drawable.ground, R.drawable.type_background_ground))
            add(dataToPokemonType("rock", R.drawable.rock, R.drawable.type_background_rock))
            add(dataToPokemonType("bug", R.drawable.bug, R.drawable.type_background_bug))
            add(dataToPokemonType("ghost", R.drawable.ghost, R.drawable.type_background_ghost))
            add(dataToPokemonType("steel", R.drawable.steel, R.drawable.type_background_steel))
            add(dataToPokemonType("fire", R.drawable.fire, R.drawable.type_background_fire))
            add(dataToPokemonType("water", R.drawable.water, R.drawable.type_background_water))
            add(dataToPokemonType("grass", R.drawable.grass, R.drawable.type_background_grass))
            add(dataToPokemonType("psychic",R.drawable.psychic,R.drawable.type_background_psychic))
            add(dataToPokemonType("ice", R.drawable.ice, R.drawable.type_background_ice))
            add(dataToPokemonType("dragon", R.drawable.dragon, R.drawable.type_background_dragon))
            add(dataToPokemonType("dark", R.drawable.dark, R.drawable.type_background_dark))
            add(dataToPokemonType("fairy", R.drawable.fairy, R.drawable.type_background_fairy))
            add(dataToPokemonType("shadow", R.drawable.dark, R.drawable.type_background_dark))
        }
    }

    fun getPokemonTypeModel(type: String): PokemonTypeModel? {

        val arr = data.filter {
            it?.name.toString().contentEquals(type)
        }

        return if (arr.isNotEmpty()) arr.first() else null
    }

    fun getPokemonTypeModel(pokemonType: List<PokemonTypeSlotModel>?): PokemonTypeModel? {
        val slot = pokemonType?.first() ?: run {
            return null
        }

        slot.type?.name?.let {
            return getPokemonTypeModel(it)
        } ?: run {
            return null
        }
    }

    private fun dataToPokemonType(name: String, image: Int, background: Int): PokemonTypeModel? =
        PokemonTypeModel(name, image, background)

    private fun convert(resource: Int): Drawable? = ContextCompat.getDrawable(context, resource)


}