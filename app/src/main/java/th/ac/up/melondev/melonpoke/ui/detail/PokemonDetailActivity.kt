package th.ac.up.melondev.melonpoke.ui.detail

import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager

import kotlinx.android.synthetic.main.activity_pokemon_detail.*
import th.ac.up.melondev.melonpoke.R
import th.ac.up.melondev.melonpoke.data.model.api.*
import th.ac.up.melondev.melonpoke.data.model.local.PokemonImageModel
import th.ac.up.melondev.melonpoke.data.model.local.PokemonInformationModel
import th.ac.up.melondev.melonpoke.data.model.local.PokemonTitleModel
import th.ac.up.melondev.melonpoke.data.model.local.PokemonTypeListModel
import th.ac.up.melondev.melonpoke.data.viewmodel.DetailViewModel
import th.ac.up.melondev.melonpoke.utill.*

class PokemonDetailActivity : AppCompatActivity() {

    private val viewModel: DetailViewModel by viewModels()

    private var pokemonDetail: PokemonDetailModel? = null
    private lateinit var detailAdapter: PokemonDetailAdapter

    private var pokemonTypeLibrary: PokemonTypeLibrary = PokemonTypeLibrary(context = this)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pokemon_detail)
        restoreState(savedInstanceState)
        initialToolbar()
        initialRecyclerView()
        loadDetail(callback())
    }

    private fun callback(): NetworkCallback<List<PokemonTypeDetailModel>> =
        object : NetworkCallback<List<PokemonTypeDetailModel>> {
            override fun onSuccess(data: List<PokemonTypeDetailModel>?) {
                hideLoading()
                data?.let {
                    mergingDetailData(it)
                }
            }

            override fun onError() {
                Log.e("DRAWER", "error loading data from network")
            }

            override fun onLoading() {
                showLoading()
            }
        }

    private fun loadDetail(callback: NetworkCallback<List<PokemonTypeDetailModel>>) {
        observeViewModel(pokemonDetail?.types?.mapNotNull {
            it.type?.name
        }?.let {
            ArrayList(it)
        }, callback)
    }

    private fun observeViewModel(
        data: ArrayList<String>?,
        callback: NetworkCallback<List<PokemonTypeDetailModel>>
    ) {
        viewModel.loadPokemonTypeDetailFromList(data)
            .observe(this, Observer { model ->
                when (model.status) {
                    Status.LOADING -> {
                        callback.onLoading()
                    }
                    Status.SUCCESS -> {
                        callback.onSuccess(model.data)

                    }
                    Status.ERROR -> {
                        callback.onError()
                    }
                }
            })
    }

    private fun showLoading() {
        pokemon_detail_loading_view.visibility = View.VISIBLE
    }

    private fun hideLoading() {
        pokemon_detail_loading_view.visibility = View.GONE
    }

    private fun mergingDetailData(dataList: List<PokemonTypeDetailModel>) {
        detailAdapter.update(ArrayList<Parcelable>().apply {
            addAll(initialDetailHeader())
            addAll(initialDamageDetail(dataList))
        })
    }

    private fun initialDamageDetail(dataList: List<PokemonTypeDetailModel>): ArrayList<Parcelable> {

        var doubleDamageFrom: ArrayList<PokemonURIModel> = ArrayList()
        var halfDamageFrom: ArrayList<PokemonURIModel> = ArrayList()
        var noDamageFrom: ArrayList<PokemonURIModel> = ArrayList()
        var doubleDamageTo: ArrayList<PokemonURIModel> = ArrayList()
        var halfDamageTo: ArrayList<PokemonURIModel> = ArrayList()
        var noDamageTo: ArrayList<PokemonURIModel> = ArrayList()

        dataList.mapNotNull {

            it.damageRelations.apply {
                this?.doubleDamageFrom?.let { list ->
                    doubleDamageFrom = ArrayList(list)
                }
                this?.halfDamageFrom?.let { list ->
                    halfDamageFrom = ArrayList(list)
                }
                this?.noDamageFrom?.let { list ->
                    noDamageFrom = ArrayList(list)
                }
                this?.doubleDamageTo?.let { list ->
                    doubleDamageTo = ArrayList(list)
                }
                this?.halfDamageTo?.let { list ->
                    halfDamageTo = ArrayList(list)
                }
                this?.noDamageTo?.let { list ->
                    noDamageTo = ArrayList(list)
                }

            }
        }

        return ArrayList<Parcelable>().apply {

            if (doubleDamageFrom.isNotEmpty() || doubleDamageTo.isNotEmpty()) {
                add(PokemonTitleModel(value = "Double Damage"))
                if (doubleDamageFrom.isNotEmpty()) {
                    this.add(
                        PokemonTypeListModel(
                            title = "From",
                            typeList = doubleDamageFrom
                        )
                    )
                }
                if (doubleDamageTo.isNotEmpty()) {
                    this.add(PokemonTypeListModel(title = "To", typeList = doubleDamageTo))
                }
            }

            if (halfDamageFrom.isNotEmpty() || halfDamageTo.isNotEmpty()) {
                add(PokemonTitleModel(value = "Half Damage"))
                if (halfDamageFrom.isNotEmpty()) {
                    this.add(PokemonTypeListModel(title = "From", typeList = halfDamageFrom))
                }
                if (halfDamageTo.isNotEmpty()) {
                    this.add(PokemonTypeListModel(title = "To", typeList = halfDamageTo))
                }
            }

            if (noDamageFrom.isNotEmpty() || noDamageTo.isNotEmpty()) {
                add(PokemonTitleModel(value = "No Damage"))
                if (noDamageFrom.isNotEmpty()) {
                    this.add(PokemonTypeListModel(title = "From", typeList = noDamageFrom))
                }
                if (noDamageTo.isNotEmpty()) {
                    this.add(PokemonTypeListModel(title = "To", typeList = noDamageTo))
                }
            }

        }
    }


    private fun initialRecyclerView() {
        detailAdapter = PokemonDetailAdapter()

        detail_recyclerView.apply {
            this.layoutManager = LinearLayoutManager(context)
            this.isNestedScrollingEnabled = false
            this.onFlingListener = null
            this.adapter = detailAdapter
        }
    }

    private fun initialDetailHeader(): ArrayList<Parcelable> {
        return ArrayList<Parcelable>().apply {
            add(
                PokemonImageModel(
                    value = pokemonDetail?.sprites?.front_default,
                    pokemonType = pokemonTypeLibrary.getPokemonTypeModel(pokemonDetail?.types)
                )
            )
            add(
                PokemonInformationModel(
                    name = pokemonDetail?.name,
                    weight = pokemonDetail?.weight,
                    height = pokemonDetail?.height,
                    base_experience = pokemonDetail?.base_experience
                )
            )
            add(
                PokemonTypeListModel(
                    title = "Type",
                    typeList = convertTypeSlotToURIModel(pokemonDetail?.types)
                )
            )
        }
    }

    private fun convertTypeSlotToURIModel(list: List<PokemonTypeSlotModel>?): List<PokemonURIModel>? {
        return list?.mapNotNull {
            it.type
        } ?: run {
            null
        }
    }

    private fun restoreState(savedInstanceState: Bundle?) {
        val intent = intent.extras

        savedInstanceState?.let {
            pokemonDetail = it.getParcelable("SAVE_DATAIL")
        } ?: run {
            pokemonDetail = intent?.getParcelable("DETAIL")
            intent?.remove("DETAIL")
        }
    }

    private fun initialToolbar() {
        pokemon_detail_toolbar.title = pokemonDetail?.name?.capitalize()
        pokemon_detail_toolbar.setNavigationOnClickListener {
            finish()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putParcelable("SAVE_DATAIL", pokemonDetail)
        super.onSaveInstanceState(outState)
    }

}
