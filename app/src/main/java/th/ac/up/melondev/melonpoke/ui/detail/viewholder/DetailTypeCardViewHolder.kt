package th.ac.up.melondev.melonpoke.ui.detail.viewholder

import android.view.View
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.detail_type_card.view.*
import th.ac.up.melondev.melonpoke.data.model.local.PokemonTypeModel
import th.ac.up.melondev.melonpoke.ui.main.base.BaseViewHolder

class DetailTypeCardViewHolder(itemView: View) : BaseViewHolder<PokemonTypeModel>(itemView) {
    private val textview = itemView.detail_type_text_view
    private val imageview = itemView.detail_type_image_view


    override fun bind(data: PokemonTypeModel) {

        Glide.with(itemView.context)
            .load(data.image)
            .into(imageview)

        textview.text = data.name?.capitalize()
    }

}