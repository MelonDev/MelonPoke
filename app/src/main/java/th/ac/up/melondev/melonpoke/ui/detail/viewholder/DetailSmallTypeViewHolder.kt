package th.ac.up.melondev.melonpoke.ui.detail.viewholder

import android.os.Parcelable
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.detail_image_list_layout.view.*
import kotlinx.android.synthetic.main.detail_small_image_list_layout.view.*
import th.ac.up.melondev.melonpoke.data.model.local.PokemonSmallTypeListModel
import th.ac.up.melondev.melonpoke.ui.detail.PokemonDetailTypeAdapter
import th.ac.up.melondev.melonpoke.ui.main.base.BaseViewHolder
import th.ac.up.melondev.melonpoke.utill.PokemonTypeLibrary


class DetailSmallTypeViewHolder(itemView: View) : BaseViewHolder<Parcelable>(itemView) {
    private val title: TextView = itemView.detail_small_image_list_layout_title
    private val recyclerView: RecyclerView = itemView.detail_small_image_list_layout_recyclerView

    private lateinit var typeAdapter: PokemonDetailTypeAdapter

    fun bind(data: PokemonSmallTypeListModel) {
        title.text = data.title

        typeAdapter = PokemonDetailTypeAdapter(data.listenerPokemon)
        initialRecyclerView()
        data.typeList?.let { urimodel ->
            val list = urimodel.map { typeSlot ->
                typeSlot.name?.let { name ->
                    PokemonTypeLibrary(itemView.context).getPokemonSmallTypeModel(name)?.let {
                        it
                    }
                }
            }
            typeAdapter.update(ArrayList(list))
        }

    }

    private fun initialRecyclerView() {
        recyclerView.apply {
            this.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            this.isNestedScrollingEnabled = false
            this.onFlingListener = null
            this.adapter = typeAdapter
        }

    }
}