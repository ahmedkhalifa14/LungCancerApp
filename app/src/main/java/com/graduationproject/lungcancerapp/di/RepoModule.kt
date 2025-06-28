package com.graduationproject.lungcancerapp.di

import com.graduationproject.lungcancerapp.data.local.datastore.DataStoreManager
import com.graduationproject.lungcancerapp.data.local.tflite.ModelDataSource
import com.graduationproject.lungcancerapp.data.network.firebase.FirebaseService
import com.graduationproject.lungcancerapp.data.repository.auth.AuthRepo
import com.graduationproject.lungcancerapp.data.repository.auth.AuthRepoImpl
import com.graduationproject.lungcancerapp.data.repository.prediction.PredictionRepository
import com.graduationproject.lungcancerapp.data.repository.prediction.PredictionRepositoryImpl
import com.graduationproject.lungcancerapp.data.repository.settings.SettingsRepo
import com.graduationproject.lungcancerapp.data.repository.settings.SettingsRepoImpl
import com.graduationproject.lungcancerapp.data.repository.user.UserRepo
import com.graduationproject.lungcancerapp.data.repository.user.UserRepoImp
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
    @Provides
    @Singleton
    fun provideUserRepo(firebaseService: FirebaseService):UserRepo{
        return UserRepoImp(firebaseService)
    }

    @Provides
    @Singleton
    fun provideSettingsRepo(dataStoreManager: DataStoreManager): SettingsRepo {
        return SettingsRepoImpl(dataStoreManager)
    }

    @Provides
    @Singleton
    fun provideAuthRepo(firebaseService: FirebaseService): AuthRepo =
        AuthRepoImpl(firebaseService)

}