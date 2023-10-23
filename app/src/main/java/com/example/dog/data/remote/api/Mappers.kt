package com.example.dog.data.remote.api

import com.example.dog.domain.Dog


fun DogApiModel.toModel():Dog=
    Dog("1",this.name,this.short_description,this.sex,this.date_birth,this.urlImage)