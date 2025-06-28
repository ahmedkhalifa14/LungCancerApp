package com.graduationproject.lungcancerapp.di

import com.graduationproject.lungcancerapp.data.repository.prediction.PredictionRepository
import com.graduationproject.lungcancerapp.domin.PredictLungCancerUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {
    @Provides
    fun providePredictLungCancerUseCase(
        repository: PredictionRepository
    ): PredictLungCancerUseCase = PredictLungCancerUseCase(repository)

}