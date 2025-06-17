package com.graduationproject.lungcancerapp.di

import android.content.Context
import com.graduationproject.lungcancerapp.data.datasource.ModelDataSource
import com.graduationproject.lungcancerapp.data.datasource.TFLiteModelDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideApplicationContext(
        @ApplicationContext context: Context
    ) = context

    @Singleton
    @Provides
    fun provideModelDataSource(
        @ApplicationContext context: Context,
        modelPath: String
    ): ModelDataSource =
        TFLiteModelDataSource(context, modelPath)

    @Provides
    fun provideModelPath(): String = "lung_cancer_model.tflite"
}