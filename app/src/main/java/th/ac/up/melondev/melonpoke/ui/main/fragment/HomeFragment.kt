package th.ac.up.melondev.melonpoke.ui.main.fragment

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.home_fragment.*
import kotlinx.android.synthetic.main.home_fragment_layout.*

import th.ac.up.melondev.melonpoke.R
import th.ac.up.melondev.melonpoke.ui.main.viewmodel.HomeViewModel

class HomeFragment : Fragment(), NavigationView.OnNavigationItemSelectedListener {

    companion object {
        fun newInstance() = HomeFragment()
    }

    private lateinit var viewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.home_fragment, container, false)

        return view
    }

    override fun onStart() {
        super.onStart()
        setupNavigationDrawer()

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)
    }

    private fun setupNavigationDrawer() {

        val toggle = ActionBarDrawerToggle(
            this.activity,home_fragment_drawer_layout, home_fragment_toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        home_fragment_drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        home_fragment_nav_view.setNavigationItemSelectedListener(this)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        home_fragment_drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }


}
