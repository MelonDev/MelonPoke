package th.ac.up.melondev.melonpoke.ui.detail


import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import th.ac.up.melondev.melonpoke.R
import th.ac.up.melondev.melonpoke.data.model.local.PokemonTypeModel
import th.ac.up.melondev.melonpoke.ui.main.base.BaseViewHolder
import th.ac.up.melondev.melonpoke.ui.detail.viewholder.DetailTypeCardViewHolder
import th.ac.up.melondev.melonpoke.utill.getViewFromLayoutInflater

class PokemonDetailTypeAdapter : RecyclerView.Adapter<BaseViewHolder<PokemonTypeModel>>() {

    var data: ArrayList<PokemonTypeModel?>? = null

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<PokemonTypeModel> {

        return DetailTypeCardViewHolder(
            parent.getViewFromLayoutInflater(
                R.layout.detail_type_card
            )
        )
    }

    override fun getItemCount(): Int = data?.filterNotNull()?.size ?: 0

    override fun onBindViewHolder(holder: BaseViewHolder<PokemonTypeModel>, position: Int) {
        data?.get(position)?.let {
            holder.bind(it)
        }
    }

    fun update(newData: ArrayList<PokemonTypeModel?>?) {
        data = newData
        notifyDataSetChanged()
    }


}