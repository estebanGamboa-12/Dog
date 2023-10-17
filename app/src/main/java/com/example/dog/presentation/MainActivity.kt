package com.example.dog.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log


import android.widget.TextView

import androidx.lifecycle.Observer
import com.example.burguer.presentation.MainModelView
import com.example.dog.R

import com.example.dog.data.DogDataRepository
import com.example.dog.data.local.XmlLocalDataSource
import com.example.dog.data.remote.ApiMockRemoteDataSource
import com.example.dog.domain.Dog
import com.example.dog.domain.GetDogUseCase
import com.example.dog.domain.SaveDogUseCase


class MainActivity : AppCompatActivity() {

    val viewModel: MainModelView by lazy {
        MainModelView(
            SaveDogUseCase(DogDataRepository(XmlLocalDataSource(this), ApiMockRemoteDataSource())),
            GetDogUseCase(DogDataRepository(XmlLocalDataSource(this), ApiMockRemoteDataSource()))
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupView()
        setupObservers()
         recoverData()
        viewModel.loadDog()
    }

     private  fun setupView(){

            viewModel.saveDog(
                getBurguerInput(),
                getMinutesInput(),
                getPercentBottomInput(),
                getPercentTopInput())
            Log.d("@dev", getMinutesInput()+ getBurguerInput() +getPercentBottomInput()+getPercentTopInput())



    }
    private fun recoverData(){


            viewModel.loadDog()


    }

    private fun setupObservers() {
        val observer = Observer<MainModelView.UiState> {
            it.dog?.apply {
                bindData(this)
            }
        }
        viewModel.uiState.observe(this, observer)
    }

    //se introduce el texto en la vista.
    private fun bindData(dog: Dog) {
        setBurguerInput(dog.name)
        setMinutesInput(dog.description)
        setPercentBottomInput(dog.sex)
        setPercentTopInput(dog.date)

    }


    private fun setBurguerInput(nombre: String) {
        findViewById<TextView>(R.id.nombre).setText(nombre)
    }

    private fun setMinutesInput(descripcion: String) {
        findViewById<TextView>(R.id.descripcion).setText(descripcion)
    }

    private fun setPercentTopInput(sexo: String) {
        findViewById<TextView>(R.id.sexo).setText(sexo)
    }

    private fun setPercentBottomInput(fecha: String) {
        findViewById<TextView>(R.id.fecha).setText(fecha)
    }

    //se recogen todos los inputs
    private fun getBurguerInput():String=

        findViewById<TextView>(R.id.nombre).text.toString()
    private fun getMinutesInput():String=
        findViewById<TextView>(R.id.descripcion).text.toString()
    private fun getPercentBottomInput():String=
        findViewById<TextView>(R.id.sexo).text.toString()
    private fun getPercentTopInput():String=
        findViewById<TextView>(R.id.nombre).text.toString()

}






