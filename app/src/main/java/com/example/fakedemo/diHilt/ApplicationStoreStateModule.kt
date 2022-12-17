package com.example.fakedemo.diHilt

import com.example.fakedemo.redux.ApplicationState
import com.example.fakedemo.redux.Store
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object ApplicationStoreStateModule {

   @Provides
   @Singleton
   fun provideApplicationStoreState():Store<ApplicationState>{
      return Store(ApplicationState())
   }
}