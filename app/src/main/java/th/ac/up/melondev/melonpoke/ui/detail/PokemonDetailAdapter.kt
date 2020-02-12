package th.ac.up.melondev.melonpoke.ui.detail


import android.os.Parcelable
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.examplerecyclerviewtype.main_activity.tab_one_fragment.ViewHolder.*
import th.ac.up.melondev.melonpoke.R
import th.ac.up.melondev.melonpoke.data.model.local.*
import th.ac.up.melondev.melonpoke.ui.detail.viewholder.DetailSmallTypeViewHolder
import th.ac.up.melondev.melonpoke.ui.main.base.BaseViewHolder
import th.ac.up.melondev.melonpoke.ui.detail.viewholder.DetailTypeViewHolder
import th.ac.up.melondev.melonpoke.ui.detail.viewholder.DetailUnknownViewHolder
import th.ac.up.melondev.melonpoke.utill.getViewFromLayoutInflater
import java.util.*

class PokemonDetailAdapter : RecyclerView.Adapter<BaseViewHolder<Parcelable>>() {

    var data: ArrayList<Parcelable>? = null

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<Parcelable> {
        return when (viewType) {
            TypeOfDetailViewHolder.TYPE_TITLE -> DetailTitleViewHolder(
                parent.getViewFromLayoutInflater(
                    R.layout.detail_title_layout
                )
            )
            TypeOfDetailViewHolder.TYPE_IMAGE -> DetailImageViewHolder(
                parent.getViewFromLayoutInflater(
                    R.layout.detail_image_layout
                )
            )
            TypeOfDetailViewHolder.TYPE_INFORMATION -> DetailInformationViewHolder(
                parent.getViewFromLayoutInflater(
                    R.layout.detail_information_layout
                )
            )
            TypeOfDetailViewHolder.TYPE_TYPE -> DetailProfileViewHolder(
                parent.getViewFromLayoutInflater(
                    R.layout.detail_type_dialog_image
                )
            )
            TypeOfDetailViewHolder.TYPE_TYPE_LIST -> DetailTypeViewHolder(
                parent.getViewFromLayoutInflater(
                    R.layout.detail_image_list_layout
                )
            )

            TypeOfDetailViewHolder.TYPE_SMALL_TYPE_LIST -> DetailSmallTypeViewHolder(
                parent.getViewFromLayoutInflater(
                    R.layout.detail_small_image_list_layout
                )
            )

            else -> DetailUnknownViewHolder(parent.getViewFromLayoutInflater(R.layout.unknown_layout))
        }
    }

    override fun getItemCount(): Int = data?.size ?: 0

    override fun getItemViewType(position: Int): Int {

        return when (data?.get(position)) {
            is PokemonTitleModel -> TypeOfDetailViewHolder.TYPE_TITLE
            is PokemonImageModel -> TypeOfDetailViewHolder.TYPE_IMAGE
            is PokemonInformationModel -> TypeOfDetailViewHolder.TYPE_INFORMATION
            is PokemonTypeListModel -> TypeOfDetailViewHolder.TYPE_TYPE_LIST
            is PokemonSmallTypeListModel -> TypeOfDetailViewHolder.TYPE_SMALL_TYPE_LIST
            is PokemonTypeModel -> TypeOfDetailViewHolder.TYPE_TYPE

            else -> {
                TypeOfDetailViewHolder.TYPE_UNKNOWN
            }
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder<Parcelable>, position: Int) {
        data?.let {
            when (holder) {
                is DetailTitleViewHolder -> holder.bind(it[position] as PokemonTitleModel)
                is DetailImageViewHolder -> holder.bind(it[position] as PokemonImageModel)
                is DetailInformationViewHolder -> holder.bind(it[position] as PokemonInformationModel)
                is DetailTypeViewHolder -> holder.bind(it[position] as PokemonTypeListModel)
                is DetailProfileViewHolder -> holder.bind(it[position] as PokemonTypeModel)
                is DetailSmallTypeViewHolder -> holder.bind(it[position] as PokemonSmallTypeListModel)

            }
        }
    }

    fun update(newData: ArrayList<Parcelable>?) {
        data = newData
        notifyDataSetChanged()
    }

}