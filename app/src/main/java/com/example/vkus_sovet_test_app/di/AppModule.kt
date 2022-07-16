package com.example.vkus_sovet_test_app.di

import android.content.Context
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.vkus_sovet_test_app.R
import com.example.vkus_sovet_test_app.data.remote.ApiFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface AppModule {

    companion object {
        @Singleton
        @Provides
        fun provideApiFactory() = ApiFactory

        @Singleton
        @Provides
        fun provideGlideInstance(
            @ApplicationContext context: Context
        ) = Glide.with(context).setDefaultRequestOptions(
            RequestOptions()
                .placeholder(androidx.appcompat.R.drawable.abc_btn_default_mtrl_shape)
                .error(androidx.appcompat.R.drawable.abc_ic_clear_material)
                .diskCacheStrategy(DiskCacheStrategy.DATA)
        )
    }
}