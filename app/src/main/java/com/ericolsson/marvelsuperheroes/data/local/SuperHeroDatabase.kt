package com.ericolsson.marvelsuperheroes.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [SuperHeroLocal::class, SuperHeroDetailLocal::class], version = 1)
abstract class SuperHeroDatabase : RoomDatabase() {
    abstract fun getDAO(): SuperHeroDAO
}