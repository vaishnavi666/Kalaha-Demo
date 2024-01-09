package com.demo.kalaha.service.rule;

import com.demo.kalaha.model.*;
import lombok.extern.slf4j.Slf4j;

/**
 * This class is responsible to check the game end rules.
 *
 * @author vaishnavi
 */
@Slf4j
public class GameOver extends KalahaRule {

    /**
     * Checks if either player has no stones left in their pits, the game is finished.
     * Transfers remaining stones to each player's house, determines the result, and resets the board.
     *
     * @param game The Kalaha game instance.
     * @param currentPit The current pit.
     */
    @Override
    public void apply(Game game, Pit currentPit) {
        log.debug("Checking game end rule");

        Integer player1StoneCount = game.getBoard().getPlayer1PitStoneCount();
        Integer player2StoneCount = game.getBoard().getPlayer2PitStoneCount();

        if (player1StoneCount == 0 || player2StoneCount == 0) {
            finishGame(game, player1StoneCount, player2StoneCount);
            resetBoard(game);
        }
    }

    /**
     * Finishes the Kalaha game by updating the game status, transferring stones to player houses.     *
     * @param game The Kalaha game instance.
     * @param player1Stones The remaining stones in player 1's pits.
     * @param player2Stones The remaining stones in player 2's pits.
     */
    private void finishGame(Game game, Integer player1Stones, Integer player2Stones) {
        game.setGameStatus(GameStatus.FINISHED);

        Pit house1 = game.getBoard().getPits().get(KalahaConstants.PLAYER1_HOUSE);
        Pit house2 = game.getBoard().getPits().get(KalahaConstants.PLAYER2_HOUSE);

        house1.setStoneCount(house1.getStoneCount() + player1Stones);
        house2.setStoneCount(house2.getStoneCount() + player2Stones);

        determineResult(game, house1.getStoneCount(), house2.getStoneCount());
    }

    /**
     * Resets the Kalaha game board.
     *
     * @param game The Kalaha game instance.
     */
    private void resetBoard(Game game){
        for(Integer key: game.getBoard().getPits().keySet()){
            if(key.equals(KalahaConstants.PLAYER1_HOUSE) || key.equals(KalahaConstants.PLAYER2_HOUSE)) {
                continue;
            }
            Pit pit = game.getBoard().getPits().get(key);
            pit.setStoneCount(0);
        }
    }

    /**
     * Determines the result of the Kalaha game based on the stones in each player's house.
     *
     * @param game The Kalaha game instance.
     * @param house1StoneCount The stones in player 1's house.
     * @param house1StoneCount The stones in player 2's house.
     */
    private void determineResult(Game game, Integer house1StoneCount, Integer house2StoneCount){
        if(house1StoneCount > house2StoneCount){
            game.setWinner(game.getPlayer1());
        }else if(house1StoneCount < house2StoneCount){
            game.setWinner(game.getPlayer2());
        }else{
            game.setWinner(null);
        }
    }
}
