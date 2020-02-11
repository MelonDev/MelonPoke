package com.example.examplerecyclerviewtype.main_activity.tab_one_fragment.ViewHolder

import android.os.Parcelable
import android.view.View
import kotlinx.android.synthetic.main.detail_title_layout.view.*
import th.ac.up.melondev.melonpoke.data.model.local.PokemonTitleModel
import th.ac.up.melondev.melonpoke.ui.main.base.BaseViewHolder

class DetailTitleViewHolder(itemView: View) : BaseViewHolder<Parcelable>(itemView) {
    private val title = itemView.detail_title_layout_text

    fun bind(data: PokemonTitleModel) {
        title.text = data.value
    }

}