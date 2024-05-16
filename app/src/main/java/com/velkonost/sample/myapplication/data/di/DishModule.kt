package com.velkonost.sample.myapplication.data.di

import com.velkonost.sample.myapplication.data.dish.FakeDishRepository
import com.velkonost.sample.myapplication.domain.dish.DishRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DishModule {

    @Singleton
    @Provides
    fun provideDishRepository(): DishRepository = FakeDishRepository()

}