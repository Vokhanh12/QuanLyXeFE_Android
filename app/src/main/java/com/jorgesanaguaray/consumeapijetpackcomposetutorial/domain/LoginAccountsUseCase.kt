package com.jorgesanaguaray.consumeapijetpackcomposetutorial.domain


import com.jorgesanaguaray.consumeapijetpackcomposetutorial.domain.item.AccountItem
import com.jorgesanaguaray.consumeapijetpackcomposetutorial.domain.item.VehicleItem
import com.jorgesanaguaray.consumeapijetpackcomposetutorial.repo.AccountRepository
import javax.inject.Inject

class LoginAccountsUseCase @Inject constructor(private val accountRepository: AccountRepository) {

    suspend fun getTypeByUsernameAndPassword(username: String, password: String): AccountItem?{
        return accountRepository.getTypeByUsernameAndPassword(username, password)
    }
}