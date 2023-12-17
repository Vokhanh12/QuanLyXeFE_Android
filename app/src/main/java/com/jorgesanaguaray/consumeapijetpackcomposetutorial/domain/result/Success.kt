package com.jorgesanaguaray.consumeapijetpackcomposetutorial.domain.result

data class Success<out T>(val data: T) : Result<T>()