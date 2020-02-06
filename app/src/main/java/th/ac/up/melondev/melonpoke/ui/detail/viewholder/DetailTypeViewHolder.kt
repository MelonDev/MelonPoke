package th.ac.up.melondev.melonpoke.ui.detail.viewholder

import android.os.Parcelable
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.detail_image_list_layout.view.*
import th.ac.up.melondev.melonpoke.data.model.local.PokemonTypeListModel
import th.ac.up.melondev.melonpoke.ui.detail.PokemonDetailTypeAdapter
import th.ac.up.melondev.melonpoke.ui.main.base.BaseViewHolder
import th.ac.up.melondev.melonpoke.utill.PokemonTypeLibrary


class DetailTypeViewHolder(itemView: View) : BaseViewHolder<Parcelable>(itemView) {
    private val title: TextView = itemView.detail_image_list_layout_title
    private val recyclerView: RecyclerView = itemView.detail_image_list_layout_recyclerView

    private lateinit var typeAdapter: PokemonDetailTypeAdapter

    override fun bind(data: Parcelable) {
        val slot: PokemonTypeListModel = data as PokemonTypeListModel

        title.text = slot.title

        typeAdapter = PokemonDetailTypeAdapter()
        initialRecyclerView()
        slot.typeList?.let { urimodel ->
            val list = urimodel.map { typeSlot ->
                typeSlot.name?.let { name ->
                    PokemonTypeLibrary(itemView.context).getPokemonTypeModel(name)?.let {
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