package com.example.dog.data.remote

import com.example.dog.app.ErrorApp
import com.example.dog.domain.Dog
import com.iesam.kotlintrainning.Either
import com.iesam.kotlintrainning.right

class ApiMockRemoteDataSource() {

    fun getDog():Either<ErrorApp,Dog>{
        return Dog(0,"Burguer 85 ","22-30","-17","98").right()
    }
}