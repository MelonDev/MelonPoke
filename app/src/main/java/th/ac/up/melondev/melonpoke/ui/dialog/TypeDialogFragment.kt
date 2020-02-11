package th.ac.up.melondev.melonpoke.ui.dialog

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.detail_type_dialog.*
import th.ac.up.melondev.melonpoke.R
import th.ac.up.melondev.melonpoke.data.model.api.PokemonDetailModel
import th.ac.up.melondev.melonpoke.data.model.api.PokemonTypeDetailModel
import th.ac.up.melondev.melonpoke.data.model.api.PokemonURIResult
import th.ac.up.melondev.melonpoke.data.model.local.PokemonSmallTypeListModel
import th.ac.up.melondev.melonpoke.data.model.local.PokemonTitleModel
import th.ac.up.melondev.melonpoke.data.model.local.PokemonTypeDetailPack
import th.ac.up.melondev.melonpoke.data.model.local.PokemonTypeModel
import th.ac.up.melondev.melonpoke.ui.detail.PokemonDetailAdapter

class TypeDialogFragment : DialogFragment() {

    companion object {
        fun newInstance(data: PokemonTypeDetailPack?): TypeDialogFragment? {
            val fragment = TypeDialogFragment()
            val bundle = Bundle()
            bundle.putParcelable("POKEMON_TYPE_DETAIL_MODEL_PACK", data)
            fragment.arguments = bundle
            return fragment
        }
    }

    private lateinit var detailAdapter: PokemonDetailAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog?.window?.attributes?.windowAnimations = R.style.DialogAnimation

        return inflater.inflate(R.layout.detail_type_dialog, container)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val pack = arguments?.getParcelable<PokemonTypeDetailPack>("POKEMON_TYPE_DETAIL_MODEL_PACK")

        detail_type_dialog_back.setOnClickListener {
            this.dismiss()
        }

        initialRecyclerView()

        pack?.let {
            mergingDetailData(it)

        }
    }

    private fun initialDetailHeader(pack: PokemonTypeDetailPack): ArrayList<Parcelable> {
        return ArrayList<Parcelable>().apply {
            add(pack.type)
        }
    }

    private fun initialRecyclerView() {
        detailAdapter = PokemonDetailAdapter()

        detail_type_dialog_recyclerview.apply {
            this.layoutManager = LinearLayoutManager(context)
            this.isNestedScrollingEnabled = false
            this.onFlingListener = null
            this.adapter = detailAdapter
        }
    }

    private fun mergingDetailData(pack :PokemonTypeDetailPack) {
        detailAdapter.update(ArrayList<Parcelable>().apply {
            addAll(initialDetailHeader(pack))
            addAll(initialDamageDetail(pack))
        })
    }

    private fun initialDamageDetail(pack :PokemonTypeDetailPack): ArrayList<Parcelable> {

        var doubleDamageFrom: ArrayList<PokemonURIResult> = ArrayList()
        var halfDamageFrom: ArrayList<PokemonURIResult> = ArrayList()
        var noDamageFrom: ArrayList<PokemonURIResult> = ArrayList()
        var doubleDamageTo: ArrayList<PokemonURIResult> = ArrayList()
        var halfDamageTo: ArrayList<PokemonURIResult> = ArrayList()
        var noDamageTo: ArrayList<PokemonURIResult> = ArrayList()


        pack.detail.damageRelations.apply {
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

        return ArrayList<Parcelable>().apply {

            if (doubleDamageFrom.isNotEmpty() || doubleDamageTo.isNotEmpty()) {
                add(PokemonTitleModel(value = "Double Damage"))
                if (doubleDamageFrom.isNotEmpty()) {
                    packingPokemonTypeList(
                        title = "From",
                        typeList = doubleDamageFrom
                    )
                    this.add(
                        packingPokemonTypeList(
                            title = "From",
                            typeList = doubleDamageFrom
                        )
                    )
                }
                if (doubleDamageTo.isNotEmpty()) {
                    this.add(packingPokemonTypeList(title = "To", typeList = doubleDamageTo))
                }
            }

            if (halfDamageFrom.isNotEmpty() || halfDamageTo.isNotEmpty()) {
                add(PokemonTitleModel(value = "Half Damage"))
                if (halfDamageFrom.isNotEmpty()) {
                    this.add(packingPokemonTypeList(title = "From", typeList = halfDamageFrom))
                }
                if (halfDamageTo.isNotEmpty()) {
                    this.add(packingPokemonTypeList(title = "To", typeList = halfDamageTo))
                }
            }

            if (noDamageFrom.isNotEmpty() || noDamageTo.isNotEmpty()) {
                add(PokemonTitleModel(value = "No Damage"))
                if (noDamageFrom.isNotEmpty()) {
                    this.add(packingPokemonTypeList(title = "From", typeList = noDamageFrom))
                }
                if (noDamageTo.isNotEmpty()) {
                    this.add(packingPokemonTypeList(title = "To", typeList = noDamageTo))
                }
            }

        }
    }

    private fun packingPokemonTypeList(
        title: String,
        typeList: List<PokemonURIResult>?
    ): PokemonSmallTypeListModel = PokemonSmallTypeListModel(title,typeList)

}