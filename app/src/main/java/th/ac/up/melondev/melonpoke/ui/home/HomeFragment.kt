package th.ac.up.melondev.melonpoke.ui.home


import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jcodecraeer.xrecyclerview.XRecyclerView
import kotlinx.android.synthetic.main.home_fragment.*
import th.ac.up.melondev.melonpoke.R
import th.ac.up.melondev.melonpoke.data.model.api.PokemonDetailModel
import th.ac.up.melondev.melonpoke.data.viewmodel.HomeViewModel
import th.ac.up.melondev.melonpoke.utill.Calculator
import th.ac.up.melondev.melonpoke.utill.NetworkCallback
import th.ac.up.melondev.melonpoke.utill.Status


class HomeFragment : Fragment() {

    companion object {
        fun newInstance(): HomeFragment =
            HomeFragment()
    }

    private val viewModel: HomeViewModel by viewModels()

    private var isLoadMoreActive: Boolean = false
    private var isRefreshActive: Boolean = false

    private lateinit var homeAdapter: HomeFragmentAdapter
    private lateinit var inputMethodManager: InputMethodManager
    private var pokemonLimit = 20

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.home_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initialInputMethodManager()
        setupToolbar()
        setupFloatingActionButton()
        setupRecyclerView()
        restoreSaveState(savedInstanceState)
        setupSearchCloseButton()
        setupSearchEditButton()
    }

    private fun networkAllDetailCallback(): NetworkCallback<List<PokemonDetailModel>> =
        object : NetworkCallback<List<PokemonDetailModel>> {
            override fun onSuccess(data: List<PokemonDetailModel>?) {
                homeAdapter.update(data)
                if (isRefreshActive) {
                    isRefreshActive = false
                    home_fragment_recyclerView.refreshComplete()
                } else {
                    this@HomeFragment.context?.let {
                        hideLoading()
                    }
                }
            }

            override fun onError() {
                Log.e("DRAWER", "error loading data from network")
            }

            override fun onLoading() {
                this@HomeFragment.context?.let {
                    if (!isRefreshActive) {
                        showLoading()
                    }
                }
            }
        }

    private fun networkAllDetailMoreCallback(): NetworkCallback<List<PokemonDetailModel>> =
        object : NetworkCallback<List<PokemonDetailModel>> {
            override fun onSuccess(data: List<PokemonDetailModel>?) {
                homeAdapter.add(data)
                if (isLoadMoreActive) {
                    isLoadMoreActive = false
                    home_fragment_recyclerView.loadMoreComplete()
                }
            }

            override fun onError() {
                Log.e("DRAWER", "error loading data from network")
            }

            override fun onLoading() {
            }
        }

    private fun loadAllDetail(callback: NetworkCallback<List<PokemonDetailModel>>) {
        viewModel.loadPokemonAll(limit = 20)
            .observe(viewLifecycleOwner, Observer { model ->
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

    private fun loadAllDetailMore(
        offset: Int,
        limit: Int,
        callback: NetworkCallback<List<PokemonDetailModel>>
    ) {
        viewModel.loadPokemonAll(offset = offset, limit = limit)
            .observe(viewLifecycleOwner, Observer { model ->
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

    private fun refresh() {
        home_fragment_recyclerView.refresh()
    }

    private fun loadMore() {
        loadAllDetailMore(pokemonLimit, 20, networkAllDetailMoreCallback())
        pokemonLimit += 20
    }

    private fun setupRecyclerView() {

        context?.let {
            val mNoOfColumns: Int =
                Calculator.calculateNoOfColumns(it, 200f)

            homeAdapter = HomeFragmentAdapter()

            home_fragment_recyclerView.apply {
                this.layoutManager = GridLayoutManager(context, mNoOfColumns)
                this.isNestedScrollingEnabled = false
                this.onFlingListener = null
                this.adapter = homeAdapter

                setLoadingListener(object : XRecyclerView.LoadingListener {
                    override fun onLoadMore() {
                        loadMore()
                        isLoadMoreActive = true
                    }

                    override fun onRefresh() {
                        loadAllDetail(networkAllDetailCallback())
                        isRefreshActive = true
                    }

                })

                addOnScrollListener(object :
                    RecyclerView.OnScrollListener() {
                    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                        super.onScrolled(recyclerView, dx, dy)

                        if (dy < 0) {
                            fragment_home_fab.show()
                        } else {
                            fragment_home_fab.hide()

                        }

                    }
                })
            }
        }


    }

    private fun scrollToTop() {
        home_fragment_recyclerView.smoothScrollToPosition(0)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putParcelableArrayList("POKEMONLIST", homeAdapter.data)
        outState.putParcelableArrayList("STORE_POKEMONLIST", homeAdapter.storeData)
        outState.putInt("LIMIT", pokemonLimit)
        super.onSaveInstanceState(outState)
    }

    private fun showLoading() {
        home_fragment_loading_view.visibility = View.VISIBLE
    }

    private fun hideLoading() {
        home_fragment_loading_view.visibility = View.GONE
    }

    private fun setupSearchEditButton(){
        fragment_home_search_edit.apply {
            clearFocus()
            addTextChangedListener {
                homeAdapter.search(it?.toString())
            }
        }
    }

    private fun setupSearchCloseButton(){
        fragment_home_search_close.setOnClickListener {
            closeSearch()
        }
    }

    private fun setupFloatingActionButton(){
        fragment_home_fab.setOnClickListener {
            scrollToTop()
        }
    }

    private fun setupToolbar(){
        fragment_home_toolbar.apply {
            this.inflateMenu(R.menu.menu_main)
                this.setOnMenuItemClickListener {
                    when (it.itemId) {
                        R.id.action_refresh -> {
                            scrollToTop()
                            refresh()
                            return@setOnMenuItemClickListener true
                        }
                        R.id.action_search -> {
                            this.context?.let {
                                openSearch()
                            }
                            return@setOnMenuItemClickListener true

                        }
                        else -> {
                            return@setOnMenuItemClickListener false
                        }
                    }
                }
        }
    }

    private fun restoreSaveState(savedInstanceState :Bundle?){
        savedInstanceState?.let {
            val pokeList =
                savedInstanceState.getParcelableArrayList<PokemonDetailModel>("POKEMONLIST")
            val pokeStore =
                savedInstanceState.getParcelableArrayList<PokemonDetailModel>("STORE_POKEMONLIST")
            pokemonLimit = savedInstanceState.getInt("LIMIT")

            pokeStore?.let {
                openSearch()
                isEnableXRecyclerview(false)
            } ?: run {
                closeSearch()
                isEnableXRecyclerview(true)
            }

            homeAdapter.update(pokeList, pokeStore)

        } ?: run {
            loadAllDetail(networkAllDetailCallback())
        }
    }

    private fun initialInputMethodManager() {
        inputMethodManager =
            activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    }

    private fun clearFocus() {
        fragment_home_search_edit.apply {
            this.text = null
            this.clearFocus()
        }
        inputMethodManager.hideSoftInputFromWindow(view?.windowToken, 0)
    }

    private fun requestFocus() {
        fragment_home_search_edit.requestFocus()
        inputMethodManager.showSoftInput(
            fragment_home_search_edit,
            InputMethodManager.SHOW_IMPLICIT
        )
    }

    fun closeSearch() {
        clearFocus()
        isEnableXRecyclerview(true)
        fragment_home_toolbar.visibility = View.VISIBLE
        fragment_home_search.visibility = View.GONE
    }

    private fun openSearch() {
        requestFocus()
        isEnableXRecyclerview(false)
        fragment_home_toolbar.visibility = View.GONE
        fragment_home_search.visibility = View.VISIBLE

    }

    private fun isEnableXRecyclerview(enable: Boolean) {
        home_fragment_recyclerView.apply {
            setLoadingMoreEnabled(enable)
            setPullRefreshEnabled(enable)
        }
    }

    fun isSearchOpen(): Boolean = fragment_home_search.visibility == View.VISIBLE

}
