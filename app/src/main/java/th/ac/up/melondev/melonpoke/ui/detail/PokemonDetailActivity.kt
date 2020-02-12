package th.ac.up.melondev.melonpoke.ui.detail

import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.os.PersistableBundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager

import kotlinx.android.synthetic.main.activity_pokemon_detail.*
import th.ac.up.melondev.melonpoke.R
import th.ac.up.melondev.melonpoke.data.model.api.*
import th.ac.up.melondev.melonpoke.data.model.local.*
import th.ac.up.melondev.melonpoke.data.viewmodel.DetailViewModel
import th.ac.up.melondev.melonpoke.ui.dialog.TypeDialogFragment
import th.ac.up.melondev.melonpoke.ui.dialog.TypeLoadingDialogFragment
import th.ac.up.melondev.melonpoke.utill.*

class PokemonDetailActivity : AppCompatActivity() {

    private val viewModel: DetailViewModel by viewModels()

    private var pokemonDetail: PokemonDetailModel? = null
    private lateinit var detailAdapter: PokemonDetailAdapter

    private var pokemonTypeLibrary: PokemonTypeLibrary = PokemonTypeLibrary(context = this)

    private var loadingDialogFragment: TypeLoadingDialogFragment? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pokemon_detail)

        initialToolbar()
        initialRecyclerView()
        restoreState(savedInstanceState)
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

        var doubleDamageFrom: ArrayList<PokemonURIResult> = ArrayList()
        var halfDamageFrom: ArrayList<PokemonURIResult> = ArrayList()
        var noDamageFrom: ArrayList<PokemonURIResult> = ArrayList()
        var doubleDamageTo: ArrayList<PokemonURIResult> = ArrayList()
        var halfDamageTo: ArrayList<PokemonURIResult> = ArrayList()
        var noDamageTo: ArrayList<PokemonURIResult> = ArrayList()

        dataList.mapNotNull {

            it.damageRelations.apply {
                this?.doubleDamageFrom?.let { list ->
                    doubleDamageFrom.addAll(list)
                }
                this?.halfDamageFrom?.let { list ->
                    halfDamageFrom.addAll(list)

                }
                this?.noDamageFrom?.let { list ->
                    noDamageFrom.addAll(list)
                }
                this?.doubleDamageTo?.let { list ->
                    doubleDamageTo.addAll(list)
                }
                this?.halfDamageTo?.let { list ->
                    halfDamageTo.addAll(list)
                }
                this?.noDamageTo?.let { list ->
                    noDamageTo.addAll(list)
                }

            }
        }

        return ArrayList<Parcelable>().apply {

            if (doubleDamageFrom.isNotEmpty() || doubleDamageTo.isNotEmpty()) {
                add(PokemonTitleModel(value = "Double Damage"))
                if (doubleDamageFrom.isNotEmpty()) {

                    this.add(
                        packingPokemonTypeList(
                            title = "From",
                            typeList = doubleDamageFrom.distinctBy {
                                it.name
                            }

                        )
                    )
                }
                if (doubleDamageTo.isNotEmpty()) {
                    this.add(
                        packingPokemonTypeList(
                            title = "To",
                            typeList = doubleDamageTo.distinctBy {
                                it.name
                            })
                    )
                }
            }

            if (halfDamageFrom.isNotEmpty() || halfDamageTo.isNotEmpty()) {
                add(PokemonTitleModel(value = "Half Damage"))
                if (halfDamageFrom.isNotEmpty()) {
                    this.add(
                        packingPokemonTypeList(
                            title = "From",
                            typeList = halfDamageFrom.distinctBy {
                                it.name
                            })
                    )
                }
                if (halfDamageTo.isNotEmpty()) {
                    this.add(
                        packingPokemonTypeList(
                            title = "To",
                            typeList = halfDamageTo.distinctBy {
                                it.name
                            })
                    )
                }
            }

            if (noDamageFrom.isNotEmpty() || noDamageTo.isNotEmpty()) {
                add(PokemonTitleModel(value = "No Damage"))
                if (noDamageFrom.isNotEmpty()) {
                    this.add(
                        packingPokemonTypeList(
                            title = "From",
                            typeList = noDamageFrom.distinctBy {
                                it.name
                            })
                    )
                }
                if (noDamageTo.isNotEmpty()) {
                    this.add(packingPokemonTypeList(title = "To", typeList = noDamageTo.distinctBy {
                        it.name
                    }))
                }
            }

        }
    }

    private fun packingPokemonTypeList(
        title: String,
        typeList: List<PokemonURIResult>?
    ): PokemonTypeListModel {
        return PokemonTypeListModel(title, typeList, onTypeItemClick)
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
                packingPokemonTypeList(
                    title = "Type",
                    typeList = convertTypeSlotToURIModel(pokemonDetail?.types?.sortedBy { it.slot })
                )
            )
        }
    }

    private fun setupLoadingDialog() {
        this.loadingDialogFragment = TypeLoadingDialogFragment.newInstance()
    }

    private fun convertTypeSlotToURIModel(list: List<PokemonTypeSlotModel>?): List<PokemonURIResult>? {
        return list?.mapNotNull {
            it.type
        } ?: run {
            null
        }
    }

    private fun restoreState(savedInstanceState: Bundle?) {
        val intent = intent.extras

        hideLoading()

        savedInstanceState?.let {
            pokemonDetail = it.getParcelable("SAVE_DATAIL")
            detailAdapter.update(it.getParcelableArrayList<Parcelable>("TYPE_DATAIL"))
        } ?: run {
            pokemonDetail = intent?.getParcelable("DETAIL")
            intent?.remove("DETAIL")

            loadDetail(callback())
        }
    }

    private fun initialToolbar() {
        pokemon_detail_back.setOnClickListener {
            finish()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putParcelable("SAVE_DATAIL", pokemonDetail)
        detailAdapter.data?.let {
            outState.putParcelableArrayList("TYPE_DATAIL", it)
        }
        super.onSaveInstanceState(outState)
    }

    private val onTypeItemClick = object : PokemonTypeListener {
        override fun onClick(model: PokemonTypeModel) {

            viewModel.loadPokemonTypeDetail(model)
                .observe(this@PokemonDetailActivity, observer)

            Log.e("LOGING", "getTypeListener")
        }

        override fun writeToParcel(dest: Parcel?, flags: Int) {
        }

        override fun describeContents(): Int {
            return 0
        }
    }

    val observer = Observer<NetworkResponse<PokemonTypeDetailPack>> { model ->
        when (model.status) {
            Status.LOADING -> {
                Log.e("HELLO", "LOADING")

                resultCallback.onLoading()
            }
            Status.SUCCESS -> {
                Log.e("HELLO", "HELLO")
                resultCallback.onSuccess(model.data)

            }
            Status.ERROR -> {
                Log.e("HELLO", "ERROR")

                resultCallback.onError()
            }
        }
    }

    private val resultCallback = object : NetworkCallback<PokemonTypeDetailPack> {
        override fun onSuccess(data: PokemonTypeDetailPack?) {

            loadingDialogFragment?.dismiss()
            loadingDialogFragment = null
            data?.let {
                val dialogFragment = TypeDialogFragment.newInstance(it)
                dialogFragment?.show(supportFragmentManager, "DIALOG")
            }
        }

        override fun onError() {
        }

        override fun onLoading() {
            loadingDialogFragment?.let {
                it.show(supportFragmentManager, "LOADING")
            } ?: run {
                setupLoadingDialog()
                loadingDialogFragment?.show(supportFragmentManager, "LOADING")
            }


        }
    }

}
