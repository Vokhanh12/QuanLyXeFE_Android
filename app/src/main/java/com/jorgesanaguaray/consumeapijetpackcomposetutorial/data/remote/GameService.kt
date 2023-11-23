package com.jorgesanaguaray.consumeapijetpackcomposetutorial.data.remote

import com.jorgesanaguaray.consumeapijetpackcomposetutorial.data.remote.model.GameModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * Created by Jorge Sanaguaray
 */

class GameService @Inject constructor(private val gameApi: GameApi) {

    /*
    suspend fun getGames(): List<GameModel> {

        return withContext(Dispatchers.IO) {
            val games = gameApi.getGames()
            games.body() ?: emptyList()
        }

    }

     */

}