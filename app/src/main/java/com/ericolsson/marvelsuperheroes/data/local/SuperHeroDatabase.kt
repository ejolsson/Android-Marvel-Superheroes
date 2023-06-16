package com.ericolsson.marvelsuperheroes.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ericolsson.marvelsuperheroes.domain.SuperHero

// 1 DAO file, all functions
@Database(entities = [SuperHeroLocal::class, SuperHeroDetailLocal::class, SuperHero::class], version = 1)
abstract class SuperHeroDatabase : RoomDatabase() {
    abstract fun getDAO(): SuperHeroDAO
}