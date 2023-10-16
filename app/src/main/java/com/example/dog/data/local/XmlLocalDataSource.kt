package com.example.dog.data.local

import android.content.Context
import com.example.dog.app.ErrorApp
import com.example.dog.domain.Dog
import com.iesam.kotlintrainning.Either
import com.iesam.kotlintrainning.left
import com.iesam.kotlintrainning.right

class XmlLocalDataSource(private val context:Context) {
    private val sharedPref = context.getSharedPreferences("Dogs", Context.MODE_PRIVATE)

    fun saveDog(
        name: String,
        description: String,
        sex: String,
        date: String
    ): Either<ErrorApp, Boolean> {
        return try {
            with(sharedPref.edit()){
                putInt("id", (1..100).random())
                putString("namr", name)
                putString("description", description)
                putString("sex", sex)
                putString("date", date)
                apply()
            }
            true.right()
        }catch (ex:Exception){
            ErrorApp.UnkowError.left()
        }

    }
    fun findDog(): Either<ErrorApp, Dog> {
        return try {
            Dog(
                sharedPref.getInt("id",0),
                sharedPref.getString("name", "")!!,
                sharedPref.getString("description", "")!!,
                sharedPref.getString("sex", "")!!,
                sharedPref.getString("date", "")!!
            ).right()
        }catch (ex:Exception){
            return ErrorApp.UnkowError.left()
        }
    }
}