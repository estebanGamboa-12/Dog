package com.example.dog.data.local

import android.content.Context
import com.example.dog.app.ErrorApp
import com.example.dog.domain.Dog
import com.iesam.androidviews.app.serialization.JsonSerialization
import com.iesam.kotlintrainning.Either
import com.iesam.kotlintrainning.left
import com.iesam.kotlintrainning.right

class XmlLocalDataSource(private val context:Context,
                        private val serialization: JsonSerialization
) {
    private val sharedPref = context.getSharedPreferences("Dogs", Context.MODE_PRIVATE)
    private val dogId="1"

    fun saveDog(dog:Dog) {
      sharedPref.edit().apply(){
          putString(dogId,serialization.toJson(dog,Dog::class.java))
          apply()
      }

    }
    fun getDog(): Either<ErrorApp, Dog> {

        val jsonDog=sharedPref.getString(dogId,null)
        jsonDog?.let{
            return  serialization.fromJson(
                it,Dog::class.java
            ).right()
        }
        return ErrorApp.UnkowError.left()
    }
}