package com.ericolsson.marvelsuperheroes.di

import com.ericolsson.marvelsuperheroes.data.local.LocalDataSource
import com.ericolsson.marvelsuperheroes.data.local.LocalDataSourceImpl
import com.ericolsson.marvelsuperheroes.data.remote.RemoteDataSource
import com.ericolsson.marvelsuperheroes.data.remote.RemoteDataSourceImpl
import com.ericolsson.marvelsuperheroes.data.repository.Repository
import com.ericolsson.marvelsuperheroes.data.repository.RepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindRepository(repositoryImpl: RepositoryImpl): Repository

    @Binds
    abstract fun bindLocalDataSource(localDataSourceImpl: LocalDataSourceImpl): LocalDataSource

    @Binds
    abstract fun bindRemoteDataSource(remoteDataSourceImpl: RemoteDataSourceImpl): RemoteDataSource

}
