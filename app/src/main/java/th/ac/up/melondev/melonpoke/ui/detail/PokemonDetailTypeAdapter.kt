package th.ac.up.melondev.melonpoke.ui.detail


import android.os.Parcelable
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.examplerecyclerviewtype.main_activity.tab_one_fragment.ViewHolder.TypeOfDetailViewHolder
import th.ac.up.melondev.melonpoke.R
import th.ac.up.melondev.melonpoke.data.model.local.PokemonSmallTypeModel
import th.ac.up.melondev.melonpoke.data.model.local.PokemonTypeModel
import th.ac.up.melondev.melonpoke.ui.detail.viewholder.DetailSmallTypeCardViewHolder
import th.ac.up.melondev.melonpoke.ui.main.base.BaseViewHolder
import th.ac.up.melondev.melonpoke.ui.detail.viewholder.DetailTypeCardViewHolder
import th.ac.up.melondev.melonpoke.ui.detail.viewholder.DetailUnknownViewHolder
import th.ac.up.melondev.melonpoke.utill.PokemonTypeListener
import th.ac.up.melondev.melonpoke.utill.getViewFromLayoutInflater

class PokemonDetailTypeAdapter(private val listener: PokemonTypeListener?) :
    RecyclerView.Adapter<BaseViewHolder<Parcelable>>() {

    var data: ArrayList<Parcelable?>? = null

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<Parcelable> {
        return when (viewType) {
            TypeOfDetailViewHolder.TYPE_TYPE ->  DetailTypeCardViewHolder(
                parent.getViewFromLayoutInflater(
                    R.layout.detail_type_card
                ), listener
            )
            TypeOfDetailViewHolder.TYPE_SMALL_TYPE ->  DetailSmallTypeCardViewHolder(
                parent.getViewFromLayoutInflater(
                    R.layout.detail_type_small_card
                )
            )
            else -> DetailUnknownViewHolder(parent.getViewFromLayoutInflater(R.layout.unknown_layout))
        }
    }

    override fun getItemCount(): Int = data?.filterNotNull()?.size ?: 0

    override fun onBindViewHolder(holder: BaseViewHolder<Parcelable>, position: Int) {
        when(holder){
            is DetailTypeCardViewHolder -> {
                data?.get(position)?.let {
                    holder.bind(it as PokemonTypeModel)
                }
            }
            is DetailSmallTypeCardViewHolder -> {
                data?.get(position)?.let {
                    holder.bind(it as PokemonSmallTypeModel)
                }
            }
        }
    }

    override fun getItemViewType(position: Int): Int {

        return when (data?.get(position)) {
            is PokemonTypeModel -> TypeOfDetailViewHolder.TYPE_TYPE
            is PokemonSmallTypeModel -> TypeOfDetailViewHolder.TYPE_SMALL_TYPE
            else -> {
                TypeOfDetailViewHolder.TYPE_UNKNOWN
            }
        }
    }

    fun update(newData: ArrayList<Parcelable?>?) {
        val a : ArrayList<Parcelable?>? = ArrayList()
        newData?.map {
            a?.add(it)
        }
        data = a
        notifyDataSetChanged()
    }


}