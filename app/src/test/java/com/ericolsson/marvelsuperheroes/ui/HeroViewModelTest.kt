package com.ericolsson.marvelsuperheroes.ui

import android.util.Log
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.ericolsson.marvelsuperheroes.data.mappers.ComicsRemoteToPresentationMapper
import com.ericolsson.marvelsuperheroes.data.mappers.SeriesRemoteToPresentationMapper
import com.ericolsson.marvelsuperheroes.data.repository.Repository
import com.ericolsson.marvelsuperheroes.utils.generateSuperHeroLocalListFlow
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import com.ericolsson.marvelsuperheroes.utils.getOrAwaitValue
import com.google.common.truth.Truth

@OptIn(ExperimentalCoroutinesApi::class)
internal class HeroViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    // SUT
    private lateinit var heroViewModel: HeroViewModel

    // Dependencies
    private lateinit var repository: Repository
    private lateinit var seriesRemoteToPresentationMapper: SeriesRemoteToPresentationMapper
    private lateinit var comicsRemoteToPresentationMapper: ComicsRemoteToPresentationMapper

    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    @Before
    fun setup() {
        Dispatchers.setMain(mainThreadSurrogate)
        seriesRemoteToPresentationMapper = SeriesRemoteToPresentationMapper()
        comicsRemoteToPresentationMapper = ComicsRemoteToPresentationMapper()
        repository = mockk()
        Dispatchers.setMain(mainThreadSurrogate)
        heroViewModel = HeroViewModel(repository,seriesRemoteToPresentationMapper, comicsRemoteToPresentationMapper)
    }

    @Test
    fun `WHEN getHeroes4 EXPECT return hero list success`() = runTest {

        // Given
        coEvery { repository.getHeroes4() } returns generateSuperHeroLocalListFlow()
        Log.d("Test tag", "generateSuperHeroLocalListFlow() = ${generateSuperHeroLocalListFlow()}")

        // When
        val actual = heroViewModel.getHeroes5()
        Log.d("Test tag", "actual = $actual") // prints kotlin.unit
        val actualFlow = heroViewModel.heroListState//.getOrAwaitValue() // receiver type mismatch, prints []
        Log.d("Test tag", "actualFlow = ${actualFlow.value}")

        // Then
        // TODO: Adjust *When* element to match *Given* element
//        Truth.assertThat(actualFlow).contains(generateSuperHeroLocalListFlow())
//        Truth.assertThat(actualFlow.value).isEqualTo(generateSuperHeroLocalListFlow())
    }

    @After
    fun tearDown(){
        Dispatchers.resetMain()
        mainThreadSurrogate.close()
    }
}