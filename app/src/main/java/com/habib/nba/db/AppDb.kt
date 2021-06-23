package com.habib.nba.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.habib.nba.model.TeamResponse

@Database(entities = [TeamResponse.Team::class], version = 1)
abstract class AppDb : RoomDatabase() {

    abstract fun appDao(): AppDao
}