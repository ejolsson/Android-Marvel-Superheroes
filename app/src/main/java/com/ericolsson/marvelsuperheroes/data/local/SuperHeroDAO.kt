package com.ericolsson.marvelsuperheroes.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface SuperHeroDAO {
    @Query("SELECT * FROM superheros")
    fun getAllSuperheros(): Flow<List<SuperHeroLocal>>

    @Query("SELECT * FROM superheros WHERE id = :id")
    fun getHeroByName(id: Long): SuperHeroLocal

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(superHeroLocal: List<SuperHeroLocal>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSuperhero(superHeroLocal: SuperHeroLocal)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSuperHeroLocal(superHeroLocal: SuperHeroLocal)

    @Delete
    fun deleteSuperHeroLocal(superHeroLocal: SuperHeroLocal)

//    @Query("SELECT COUNT(*) FROM superheros WHERE favorite = 1")
//    fun countAllFavs(superHeroLocalList: List<SuperHeroLocal>): Int
}