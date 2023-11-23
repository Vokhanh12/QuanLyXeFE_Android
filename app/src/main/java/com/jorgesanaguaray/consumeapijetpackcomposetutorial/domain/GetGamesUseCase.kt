package com.jorgesanaguaray.consumeapijetpackcomposetutorial.domain

import com.jorgesanaguaray.consumeapijetpackcomposetutorial.repo.GameRepository
import javax.inject.Inject

/**
 * Created by Jorge Sanaguaray
 */

class GetGamesUseCase @Inject constructor(private val gameRepository: GameRepository) {

    /*
    suspend operator fun invoke(): List<GameItem> {

        return gameRepository.getGames().shuffled()

    }

     */

}