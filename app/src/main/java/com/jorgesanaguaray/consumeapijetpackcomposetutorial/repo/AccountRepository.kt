package com.jorgesanaguaray.consumeapijetpackcomposetutorial.repo

import com.jorgesanaguaray.consumeapijetpackcomposetutorial.data.remote.AccountService
import com.jorgesanaguaray.consumeapijetpackcomposetutorial.data.remote.model.AccountModel
import com.jorgesanaguaray.consumeapijetpackcomposetutorial.domain.item.AccountItem
import javax.inject.Inject

class AccountRepository @Inject constructor(private val accountService: AccountService) {

    suspend fun getTypeByUsernameAndPassword(username: String, password: String): AccountItem?{
        return accountService.getTypeByUsernameAndPassword(username, password)
    }

}