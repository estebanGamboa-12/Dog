package com.example.dog.domain

import com.example.dog.app.ErrorApp
import com.iesam.kotlintrainning.Either

class GetDogUseCase(private val repository: DogRepository) {

    operator  fun invoke():Either<ErrorApp,Dog>{
        return  repository.obtain()
    }
}