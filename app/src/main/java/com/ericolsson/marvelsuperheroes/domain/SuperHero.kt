package com.ericolsson.marvelsuperheroes.domain

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "superheros2")
data class SuperHero(
    @PrimaryKey val id: Long,
    val name: String,
    val photo: String,
    val description: String,
    var favorite: Boolean
) : Parcelable