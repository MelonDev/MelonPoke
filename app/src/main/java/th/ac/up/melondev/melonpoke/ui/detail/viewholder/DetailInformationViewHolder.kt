package com.example.examplerecyclerviewtype.main_activity.tab_one_fragment.ViewHolder

import android.os.Parcelable
import android.view.View
import kotlinx.android.synthetic.main.detail_information_layout.view.*
import th.ac.up.melondev.melonpoke.data.model.local.PokemonInformationModel
import th.ac.up.melondev.melonpoke.ui.main.base.BaseViewHolder

class DetailInformationViewHolder(itemView: View) : BaseViewHolder<Parcelable>(itemView) {

    private val name = itemView.detail_information_layout_name
    private val weight = itemView.detail_information_weight_text
    private val height = itemView.detail_information_height_text
    private val experience = itemView.detail_information_be_text


    override fun bind(data: Parcelable) {
        val info = data as PokemonInformationModel

        name.text = info.name?.capitalize()
        weight.text = info.weight.toString()
        height.text = info.height.toString()
        experience.text = info.base_experience.toString()

    }

}