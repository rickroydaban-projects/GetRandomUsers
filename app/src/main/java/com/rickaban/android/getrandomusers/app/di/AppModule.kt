package com.rickaban.android.getrandomusers.app.di

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.Provides
import com.rickaban.android.getrandomusers.feature.random.domain.RandomRepository
import com.rickaban.android.getrandomusers.feature.random.domain.AppRandomRepository

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    fun provideRandomRepository(): RandomRepository =
        AppRandomRepository()
}