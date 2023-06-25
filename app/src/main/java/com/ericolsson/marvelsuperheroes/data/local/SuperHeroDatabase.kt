package com.ericolsson.marvelsuperheroes.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ericolsson.marvelsuperheroes.domain.SuperHero

@Database(entities = [
    SuperHeroLocal::class,
    SuperHeroDetailLocal::class,
    SuperHero::class], version = 1)
abstract class SuperHeroDatabase : RoomDatabase() {
    abstract fun getDAO(): SuperHeroDAO
}
/**
 * Entities represent class types that get saved to this DB
 */