package th.ac.up.melondev.melonpoke

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4

import org.junit.runner.RunWith

import org.junit.Rule
import th.ac.up.melondev.melonpoke.data.model.api.PokemonDrawerModel
import th.ac.up.melondev.melonpoke.utill.NetworkResponse


@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private var model :NetworkResponse<PokemonDrawerModel>? = null



}
