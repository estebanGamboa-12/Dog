package com.example.burguer.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.example.dog.app.ErrorApp
import com.example.dog.domain.Dog
import com.example.dog.domain.GetDogUseCase
import com.example.dog.domain.SaveDogUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainModelView (
    private val saveDogUseCase: SaveDogUseCase,
    private val getDogUseCase: GetDogUseCase
):ViewModel(){

    private val  _uiState= MutableLiveData<UiState>()
    val uiState: LiveData<UiState> = _uiState

    fun saveDog(name:String, minutes:String, porcentajeTop:String,porcentajeBottom:String ){
        saveDogUseCase(name,minutes,porcentajeTop,porcentajeBottom).fold(
            { responseError(it) },
            { responseSuccess(it) }
        )
    }

    fun loadDog(){
        viewModelScope.launch(Dispatchers.IO) {
            getDogUseCase().fold(
                { responseError(it) },
                { responseGetUserSuccess(it) }
            )
        }
    }
    //Errores
    private fun responseError(errorApp: ErrorApp) {

    }

    private fun responseSuccess(isOk: Boolean) {

    }

    private fun responseGetUserSuccess(burguer: Dog) {
        _uiState.postValue(UiState(burguer = burguer))
    }
    data class UiState(
        val errorApp: ErrorApp? = null,
        val isLoading: Boolean = false,
        val burguer: Dog? = null
    )
}