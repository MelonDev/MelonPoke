package th.ac.up.melondev.melonpoke

import android.util.Log
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.observe
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import th.ac.up.melondev.melonpoke.data.model.PokemonDrawerModel
import th.ac.up.melondev.melonpoke.data.viewmodel.HomeViewModel
import th.ac.up.melondev.melonpoke.utill.NetworkResponse
import th.ac.up.melondev.melonpoke.utill.Status
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeoutException


@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private var model :NetworkResponse<PokemonDrawerModel>? = null

    /*@Before
    fun setUp(){
        model = getPokemonDrawerModel()
    }

     */


    @Test
    fun totalNumberOfPokemon(){
        assertEquals(600,getPokemonDrawerModel())
    }

    private fun getPokemonDrawerModel() :NetworkResponse<PokemonDrawerModel>{
        val viewModel = HomeViewModel()
        return  viewModel.loadData().getOrAwaitValue()
    }

    private fun <T> LiveData<T>.getOrAwaitValue(
        time: Long = 2,
        timeUnit: TimeUnit = TimeUnit.SECONDS
    ): T {
        var data: T? = null
        val latch = CountDownLatch(1)
        val observer = object : Observer<T> {
            override fun onChanged(o: T?) {
                data = o
                latch.countDown()
                this@getOrAwaitValue.removeObserver(this)
            }
        }

        this.observeForever(observer)

        if (!latch.await(time, timeUnit)) {
            throw TimeoutException("LiveData value was never set.")
        }

        @Suppress("UNCHECKED_CAST")
        return data as T
    }

}
