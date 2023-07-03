package com.ericolsson.marvelsuperheroes.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.ericolsson.marvelsuperheroes.data.mappers.ComicsRemoteToPresentationMapper
import com.ericolsson.marvelsuperheroes.data.mappers.SeriesRemoteToPresentationMapper
import com.ericolsson.marvelsuperheroes.data.repository.Repository
import com.ericolsson.marvelsuperheroes.ui.heroes.heroesSample
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mock
import org.mockito.Mockito
import kotlinx.coroutines.test.setMain
import org.mockito.Mockito.mock
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import org.junit.After


class HeroViewModelTestMocks {

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    private lateinit var repository: Repository

    @Mock
    private lateinit var seriesRemoteToPresentationMapper: SeriesRemoteToPresentationMapper

    @Mock
    private lateinit var comicsRemoteToPresentationMapper: ComicsRemoteToPresentationMapper

    private lateinit var viewModel: HeroViewModel
    private val testDispatcher = TestCoroutineDispatcher()

    @Before
    fun setup() {
        repository = mock(Repository::class.java)
        seriesRemoteToPresentationMapper = mock(SeriesRemoteToPresentationMapper::class.java)
        comicsRemoteToPresentationMapper = mock(ComicsRemoteToPresentationMapper::class.java)
        viewModel = HeroViewModel(repository, seriesRemoteToPresentationMapper, comicsRemoteToPresentationMapper)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `getHeroes5 should update heroListState2`() = runTest {

        // Given
        Dispatchers.setMain(testDispatcher)
        val heroes = heroesSample
        Mockito.`when`(repository.getHeroes4()).thenReturn(flowOf(heroes))

        // When
        viewModel.getHeroes5()

        // Then
        Mockito.verify(repository).getHeroes4()
        advanceUntilIdle()
        Assert.assertEquals(heroes, viewModel.heroListState.value)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun cleanup() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }
}