package com.example.dog.domain

import com.example.dog.app.ErrorApp
import com.iesam.kotlintrainning.Either

interface DogRepository {
    fun save( name:String,description:String,sex:String,date:String): Either<ErrorApp, Boolean>

    fun obtain(): Either<ErrorApp, Dog>
}