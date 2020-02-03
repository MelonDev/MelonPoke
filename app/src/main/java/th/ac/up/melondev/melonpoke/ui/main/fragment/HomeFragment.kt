package th.ac.up.melondev.melonpoke.ui.main.fragment


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import th.ac.up.melondev.melonpoke.R
import th.ac.up.melondev.melonpoke.utill.Status
import th.ac.up.melondev.melonpoke.data.viewmodel.HomeViewModel

class HomeFragment : Fragment() {

    companion object {
        fun newInstance() = HomeFragment()
    }

    private val viewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.home_fragment, container, false)

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.loadData().observe(viewLifecycleOwner, Observer { model ->
            when (model.status) {
                Status.LOADING -> {
                    //message.text = "loading data from network"
                }
                Status.SUCCESS -> {
                    val drawer  = model.data
                    drawer?.let {
                        //message.text = person.firstName + " " + person.lastName + "\n" + person.email
                        Log.e("DRAWER",it.count.toString())
                    }
                }
                Status.ERROR -> {
                    //essage.text = "error loading data from network"
                }
            }
        })
    }


}
