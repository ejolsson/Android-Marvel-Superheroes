package com.ericolsson.marvelsuperheroes.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SuperHero(
    val id: Long,
    val name: String,
    val photo: String,
    val description: String
) : Parcelable