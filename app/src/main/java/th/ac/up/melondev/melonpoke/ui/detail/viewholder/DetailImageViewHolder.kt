package com.example.examplerecyclerviewtype.main_activity.tab_one_fragment.ViewHolder


import android.os.Parcelable
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import kotlinx.android.synthetic.main.detail_image_layout.view.*

import th.ac.up.melondev.melonpoke.data.model.local.PokemonImageModel
import th.ac.up.melondev.melonpoke.ui.main.base.BaseViewHolder


class DetailImageViewHolder(itemView: View) : BaseViewHolder<Parcelable>(itemView) {

    private val imageView: ImageView = itemView.detail_image_layout_imageview
    private val background: ImageView = itemView.detail_image_layout_imageview_bg


    fun bind(data: PokemonImageModel) {

        Glide.with(itemView.context)
            .load(data.value)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(imageView)

        Glide.with(itemView.context)
            .load(data.pokemonType?.background)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(background)


    }

}