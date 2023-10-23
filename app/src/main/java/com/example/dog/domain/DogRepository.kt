package com.example.dog.domain

import com.example.dog.app.ErrorApp
import com.iesam.kotlintrainning.Either

interface DogRepository {

   suspend fun obtain(): Either<ErrorApp, Dog>
}