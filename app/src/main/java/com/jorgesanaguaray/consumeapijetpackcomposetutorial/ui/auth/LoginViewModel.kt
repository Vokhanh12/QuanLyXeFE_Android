package com.jorgesanaguaray.consumeapijetpackcomposetutorial.ui.auth

import androidx.lifecycle.ViewModel
import com.jorgesanaguaray.consumeapijetpackcomposetutorial.domain.GetAccountsUseCase
import com.jorgesanaguaray.consumeapijetpackcomposetutorial.domain.item.AccountItem
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val loginAccountsUseCase: GetAccountsUseCase): ViewModel() {
    suspend fun getTypeByUsernameAndPassword(username: String, password: String): AccountItem?{
        return loginAccountsUseCase.getTypeByUsernameAndPassword(username, password)
    }

}