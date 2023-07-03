package com.ericolsson.marvelsuperheroes.data

import com.ericolsson.marvelsuperheroes.data.local.LocalDataSource
import com.ericolsson.marvelsuperheroes.data.local.SuperHeroDAO
import com.ericolsson.marvelsuperheroes.data.mappers.RemoteToLocalMapper
import com.ericolsson.marvelsuperheroes.data.remote.FakeRemoteDataSource
import com.ericolsson.marvelsuperheroes.data.repository.RepositoryImpl
import com.ericolsson.marvelsuperheroes.ui.heroSample
import com.google.common.truth.Truth
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class RepositoryImplWithFakesTest {

    // SUT
    private lateinit var repositoryImpl: RepositoryImpl
    private lateinit var localDataSource: LocalDataSource
    private val fakeRemoteDataSource = FakeRemoteDataSource()
    private lateinit var dao: SuperHeroDAO

    @Before
    fun setUpMocks() {
        localDataSource = mockk()
        dao = mockk()
        repositoryImpl = RepositoryImpl(
            localDataSource,
            fakeRemoteDataSource,
            RemoteToLocalMapper(),
            dao
        )
    }

    @Test
    fun `WHEN getHeroByName4 EXPECT success and return SuperHeroLocal` () = runTest {

        // Given
        val expectedHeroLocal = heroSample
        coEvery { localDataSource.getHeroByName3(1009664) } returns expectedHeroLocal

        // When
        val actual = repositoryImpl.getHeroByName4(1009664)

        // Then
        Truth.assertThat(actual).isNotNull()
        Truth.assertThat(actual?.id).isEqualTo(1009664)
    }
}