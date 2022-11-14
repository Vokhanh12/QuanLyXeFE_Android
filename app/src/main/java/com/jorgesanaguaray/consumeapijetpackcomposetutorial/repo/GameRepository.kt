package com.jorgesanaguaray.consumeapijetpackcomposetutorial.repo

import com.jorgesanaguaray.consumeapijetpackcomposetutorial.data.remote.GameService
import com.jorgesanaguaray.consumeapijetpackcomposetutorial.domain.item.GameItem
import com.jorgesanaguaray.consumeapijetpackcomposetutorial.domain.item.toGameItem
import javax.inject.Inject

/**
 * Created by Jorge Sanaguaray
 */

class GameRepository @Inject constructor(private val gameService: GameService) {

    suspend fun getGames(): List<GameItem> {

        return gameService.getGames().map {
            it.toGameItem()
        }

    }

}