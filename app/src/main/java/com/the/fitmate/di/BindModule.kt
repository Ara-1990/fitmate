package com.the.fitmate.di

import com.the.fitmate.data.SaveGetExerReposImpl
import com.the.fitmate.domain.SaveGetExerRepos
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class BindModule {
    @Binds
    @Singleton
    abstract fun bindSaveGetExerRepos(
        impl: SaveGetExerReposImpl
    ): SaveGetExerRepos

}