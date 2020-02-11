package th.ac.up.melondev.melonpoke.ui.home

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import th.ac.up.melondev.melonpoke.R
import th.ac.up.melondev.melonpoke.data.model.api.PokemonDetailModel
import th.ac.up.melondev.melonpoke.ui.main.base.BaseViewHolder
import th.ac.up.melondev.melonpoke.utill.getViewFromLayoutInflater

class HomeFragmentAdapter : RecyclerView.Adapter<HomeFragmentViewHolder>() {

    var data: ArrayList<PokemonDetailModel>? = ArrayList()
    var storeData: ArrayList<PokemonDetailModel>? = null

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HomeFragmentViewHolder {
        return HomeFragmentViewHolder(
            parent.getViewFromLayoutInflater(
                R.layout.item_home_fragment
            )
        )
    }

    override fun getItemCount(): Int = data?.size ?: 0

    override fun onBindViewHolder(holder: HomeFragmentViewHolder, position: Int) {

        data?.let {
            holder.bind(it[position])
        }
    }

    fun update(newData: List<PokemonDetailModel>?) {
        data?.clear()
        addData(newData)
    }

    fun update(dataList: List<PokemonDetailModel>?, storeList: List<PokemonDetailModel>?) {
        data?.clear()
        storeData = null
        addData(dataList)
        addStoreData(storeList)
    }

    fun add(newData: List<PokemonDetailModel>?) {
        addData(newData)
    }

    fun search(word: String?) {
        storeData?.let {
            searching(word)
        } ?: run {
            data?.let {
                addStoreData(it)
            }
            searching(word)
        }
    }

    private fun searching(word: String?) {
        word?.let { str ->
            if (str.isEmpty()) {
                clearData()
            } else {
                val data: List<PokemonDetailModel> = ArrayList<PokemonDetailModel>().apply {
                    storeData?.let { this.addAll(it) }
                }.filter { pokemon ->
                    pokemon.name?.contains(str,true) ?: false
                }
                update(data)
            }
        }
    }

    private fun clearData() {
        update(storeData)
        storeData = null
    }

    private fun addData(newData: List<PokemonDetailModel>?) {
        newData?.let {
            data?.addAll(it)
            notifyDataSetChanged()
        }
    }

    private fun addStoreData(newData: List<PokemonDetailModel>?) {
        newData?.let {
            storeData = ArrayList<PokemonDetailModel>().apply {
                addAll(it)
            }
            notifyDataSetChanged()
        }
    }


}