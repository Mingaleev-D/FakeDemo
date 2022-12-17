package com.example.fakedemo

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
 * @author : Mingaleev D
 * @data : 16/12/2022
 */

@HiltAndroidApp
class MyFakeDemoApp:Application() {

   override fun onCreate() {
      super.onCreate()
      //to do thing if i wanted to
   }
}