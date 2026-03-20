package com.vizo.app.di

import com.vizo.app.data.repository.AffiliateRepository
import com.vizo.app.data.repository.AuthRepository
import com.vizo.app.data.repository.ContactRepository
import com.vizo.app.data.repository.SubscriptionRepository
import com.vizo.app.data.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideAuthRepository(): AuthRepository {
        return AuthRepository()
    }

    @Provides
    @Singleton
    fun provideUserRepository(): UserRepository {
        return UserRepository()
    }

    @Provides
    @Singleton
    fun provideContactRepository(): ContactRepository {
        return ContactRepository()
    }

    @Provides
    @Singleton
    fun provideSubscriptionRepository(): SubscriptionRepository {
        return SubscriptionRepository()
    }

    @Provides
    @Singleton
    fun provideAffiliateRepository(): AffiliateRepository {
        return AffiliateRepository()
    }
}
