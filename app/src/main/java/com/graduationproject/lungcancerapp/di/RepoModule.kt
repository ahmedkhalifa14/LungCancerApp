package com.graduationproject.lungcancerapp.di

import com.graduationproject.lungcancerapp.data.datasource.ModelDataSource
import com.graduationproject.lungcancerapp.data.repository.PredictionRepository
import com.graduationproject.lungcancerapp.data.repository.PredictionRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepoModule {
    @Provides
    @Singleton
    fun providePredictionRepository(
        modelDataSource: ModelDataSource
    ): PredictionRepository = PredictionRepositoryImpl(modelDataSource)
}