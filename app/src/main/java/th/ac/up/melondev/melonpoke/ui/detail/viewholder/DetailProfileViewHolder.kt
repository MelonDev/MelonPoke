package com.example.examplerecyclerviewtype.main_activity.tab_one_fragment.ViewHolder


import android.os.Parcelable
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.detail_type_dialog_image.view.*

import th.ac.up.melondev.melonpoke.data.model.local.PokemonTypeModel
import th.ac.up.melondev.melonpoke.ui.main.base.BaseViewHolder


class DetailProfileViewHolder(itemView: View) : BaseViewHolder<Parcelable>(itemView) {

    private val imageView: ImageView = itemView.detail_type_dialog_image_image
    private val background: ImageView = itemView.detail_type_dialog_image_bg
    private val textView :TextView = itemView.detail_type_dialog_image_title

    fun bind(data: PokemonTypeModel) {

        Glide.with(itemView.context)
            .load(data.image)
            .into(imageView)

        Glide.with(itemView.context)
            .load(data.background)
            .into(background)

        textView.text = data.name?.capitalize()



    }

}