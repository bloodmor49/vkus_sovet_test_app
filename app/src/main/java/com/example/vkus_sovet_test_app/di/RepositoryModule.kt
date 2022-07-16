package com.example.vkus_sovet_test_app.di

import com.example.vkus_sovet_test_app.data.RepositoryImpl
import com.example.vkus_sovet_test_app.domain.AppRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Singleton

@ExperimentalCoroutinesApi
@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    @Singleton
    fun bindWeatherRepository(repositoryImpl: RepositoryImpl): AppRepository

}