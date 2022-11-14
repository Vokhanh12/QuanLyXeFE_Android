package com.jorgesanaguaray.consumeapijetpackcomposetutorial.domain.item

import com.jorgesanaguaray.consumeapijetpackcomposetutorial.data.remote.model.GameModel

/**
 * Created by Jorge Sanaguaray
 */

data class GameItem(

    val id: Int,
    val title: String,
    val thumbnail: String,
    val short_description: String

)

fun GameModel.toGameItem() = GameItem(id, title, thumbnail, short_description)
