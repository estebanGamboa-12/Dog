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
    override fun save(name:String,  description:String, sex:String, date:String): Either<ErrorApp, Boolean> {
        return localDataSource.saveDog(name,description,sex,date)
    }
    override fun obtain(): Either<ErrorApp, Dog> {
        return apiMockRemoteDataSource.getDog()
    }
}