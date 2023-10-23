package com.example.dog.data

import com.example.dog.app.ErrorApp
import com.example.dog.data.local.XmlLocalDataSource
import com.example.dog.data.remote.ApiMockRemoteDataSource
import com.example.dog.domain.Dog
import com.example.dog.domain.DogRepository
import com.iesam.kotlintrainning.Either

class DogDataRepository(
    private val localDataSource: XmlLocalDataSource,
    private val apiMockRemoteDataSource: ApiMockRemoteDataSource
): DogRepository {

    override suspend fun obtain(): Either<ErrorApp, Dog> {
    var dog=localDataSource.getDog()
        dog.mapLeft {
            return  apiMockRemoteDataSource.getDog().map {
                localDataSource.saveDog(it)
                it
            }
        }
        return dog


    }
}