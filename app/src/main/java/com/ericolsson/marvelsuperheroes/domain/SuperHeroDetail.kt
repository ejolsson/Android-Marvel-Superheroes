package com.ericolsson.marvelsuperheroes.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SuperHeroDetail(
    val id: String,
    val name: String,
    val photo: String,
    val description: String,
    val favourite: Boolean
) : Parcelable