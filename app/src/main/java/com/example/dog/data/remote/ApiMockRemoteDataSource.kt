package com.example.dog.data.remote

import com.example.dog.app.ErrorApp
import com.example.dog.data.remote.api.DogApiModel
import com.example.dog.data.remote.api.DogApiService
import com.example.dog.data.remote.api.toModel
import com.example.dog.domain.Dog
import com.iesam.kotlintrainning.Either
import com.iesam.kotlintrainning.left
import com.iesam.kotlintrainning.right
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.UnknownHostException
import java.util.concurrent.TimeoutException

class ApiMockRemoteDataSource() {

    private val baseurl = "https://dam.sitehub.es/curso-2023-2024/api/"
    suspend fun getDog(): Either<ErrorApp, Dog> {

        val retrofit = Retrofit.Builder()
            .baseUrl(baseurl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service: DogApiService = retrofit.create(DogApiService::class.java)

        try {
            val repos: Response<DogApiModel> = service.getDog()

            if (repos.isSuccessful) {
                return repos.body()!!.toModel().right()
            } else {
                return ErrorApp.UnkowError.left()
            }
        } catch (ex: TimeoutException) {
            return ErrorApp.UnkowError.left()
        } catch (ex: UnknownHostException) {
            return ErrorApp.UnkowError.left()
        } catch (ex: Exception) {
            return ErrorApp.UnkowError.left()
        }
    }

}
