package th.ac.up.melondev.melonpoke.ui.main

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import th.ac.up.melondev.melonpoke.R
import th.ac.up.melondev.melonpoke.ui.home.HomeFragment


class MainActivity : AppCompatActivity() {

    private lateinit var fragment: Fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        savedInstanceState?.let {
            supportFragmentManager.getFragment(it,"FRAGMENT")?.let {currentFragment ->
                fragment = currentFragment
            }
        } ?: run {
            initialFragment()
        }
    }

    override fun onBackPressed() {
        val home = fragment as HomeFragment

        if(home.isSearchOpen()){
            home.closeSearch()
        }else {
            super.onBackPressed()
        }
    }

    private fun initialFragment() {
        this.fragment = HomeFragment.newInstance()
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.main_activity_frame, fragment)
            .commit()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        supportFragmentManager.putFragment(outState,"FRAGMENT",fragment)
        super.onSaveInstanceState(outState)
    }


}
