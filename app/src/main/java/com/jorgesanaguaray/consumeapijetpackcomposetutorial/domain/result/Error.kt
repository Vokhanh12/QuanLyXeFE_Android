package com.jorgesanaguaray.consumeapijetpackcomposetutorial.domain.result

data class Error(val exception: Exception) : Result<Nothing>()
