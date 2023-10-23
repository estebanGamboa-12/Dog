package com.example.dog.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle


import androidx.lifecycle.Observer
import com.example.burguer.presentation.MainModelView
import com.example.dog.app.extension.loadUrl

import com.example.dog.data.DogDataRepository
import com.example.dog.data.local.XmlLocalDataSource
import com.example.dog.data.remote.ApiMockRemoteDataSource
import com.example.dog.databinding.ActivityMainBinding
import com.example.dog.domain.Dog
import com.example.dog.domain.GetDogUseCase
import com.iesam.androidviews.app.serialization.GsonSerialization


class MainActivity : AppCompatActivity() {

    val viewModel: MainModelView by lazy {
        MainModelView(
            GetDogUseCase(
                DogDataRepository(
                    XmlLocalDataSource(
                        this,
                        GsonSerialization()
                    ), ApiMockRemoteDataSource()))
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       setupBinding()
        setupObservers()
        viewModel.loadDog()
    }

    private lateinit var binding: ActivityMainBinding

    private fun setupBinding(){
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
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
    binding.apply {
        name.text=dog.name;
        shortDescription.text=dog.description
        sex.text=dog.sex
        dateBirth.text=dog.date
        urlImage.loadUrl(dog.urlImage)
    }

    }
}






