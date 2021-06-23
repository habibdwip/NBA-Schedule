package com.habib.nba

import android.app.Application
import androidx.room.Room
import com.habib.nba.db.AppDb

class App : Application() {

    companion object {
        lateinit var db: AppDb
    }

    override fun onCreate() {
        super.onCreate()
        db = Room.databaseBuilder(applicationContext, AppDb::class.java, "appDb")
            .allowMainThreadQueries().build()
    }
}