package com.ericolsson.marvelsuperheroes.data.repository

import com.ericolsson.marvelsuperheroes.data.remote.RemoteDataSource
import com.ericolsson.marvelsuperheroes.data.remote.RemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindsRepository(repositoryImpl: RepositoryImpl): Repository

//    @Binds
//    abstract fun bindsLocalDataSource(localDataSourceImpl: LocalDataSourceImpl): LocalDataSource // already an interface

    @Binds
    abstract fun bindsRemoteDataSource(remoteDataSourceImpl: RemoteDataSourceImpl): RemoteDataSource
}