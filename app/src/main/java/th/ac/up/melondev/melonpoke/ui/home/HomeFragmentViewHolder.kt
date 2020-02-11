package th.ac.up.melondev.melonpoke.ui.home

import android.content.Intent
import android.graphics.drawable.Drawable
import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import kotlinx.android.synthetic.main.item_home_fragment.view.*
import th.ac.up.melondev.melonpoke.data.model.api.PokemonDetailModel
import th.ac.up.melondev.melonpoke.ui.detail.PokemonDetailActivity
import th.ac.up.melondev.melonpoke.ui.main.base.BaseViewHolder


class HomeFragmentViewHolder(itemView: View) : BaseViewHolder<PokemonDetailModel>(itemView) {
    fun bind(data: PokemonDetailModel) {


        Glide.with(itemView.context)
            .load(data.sprites?.front_default)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(itemView.imgMember)

        itemView.textMemberNickName.text = data.name?.capitalize()

        itemView.setOnClickListener {
            val intent = Intent(it.context, PokemonDetailActivity::class.java)
            intent.putExtra("DETAIL",data)
            it.context.startActivity(intent)
        }

    }

}