package th.ac.up.melondev.melonpoke.ui.detail.viewholder

import android.os.Parcelable
import android.util.Log
import android.view.View
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.detail_type_card.view.*
import kotlinx.android.synthetic.main.detail_type_small_card.view.*
import th.ac.up.melondev.melonpoke.data.model.local.PokemonSmallTypeModel
import th.ac.up.melondev.melonpoke.data.model.local.PokemonTypeModel
import th.ac.up.melondev.melonpoke.ui.main.base.BaseViewHolder
import th.ac.up.melondev.melonpoke.utill.PokemonTypeListener
import th.ac.up.melondev.melonpoke.utill.TypeDetailDialog

class DetailSmallTypeCardViewHolder(itemView: View) : BaseViewHolder<Parcelable>(itemView) {
    private val imageview = itemView.detail_small_type_image_view

    fun bind(data: PokemonSmallTypeModel) {
        Glide.with(itemView.context)
            .load(data.image)
            .into(imageview)

    }



}