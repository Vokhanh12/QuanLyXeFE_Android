package com.jorgesanaguaray.consumeapijetpackcomposetutorial.domain.item

import com.jorgesanaguaray.consumeapijetpackcomposetutorial.data.remote.model.AccountModel
data class AccountItem(
    val type: String?
)
fun AccountModel.toDto() = AccountModel(this.type)