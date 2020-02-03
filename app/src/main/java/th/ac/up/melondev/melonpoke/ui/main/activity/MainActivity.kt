package th.ac.up.melondev.melonpoke.ui.main.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

import th.ac.up.melondev.melonpoke.R
import th.ac.up.melondev.melonpoke.ui.main.fragment.HomeFragment

class MainActivity : AppCompatActivity() {

    private lateinit var fragment: Fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initialFragment()

    }

    private fun initialFragment(){
        this.fragment = HomeFragment.newInstance()
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.main_activity_frame, fragment)
            .commit()
    }


}
