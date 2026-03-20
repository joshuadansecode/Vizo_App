package com.vizo.app

import android.app.Application
import com.vizo.app.data.remote.SupabaseProvider
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class VizoApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        SupabaseProvider.init(
            url = BuildConfig.SUPABASE_URL,
            key = BuildConfig.SUPABASE_KEY
        )
    }
}
