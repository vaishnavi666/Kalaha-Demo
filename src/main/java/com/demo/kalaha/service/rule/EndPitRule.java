package com.demo.kalaha.service.rule;

import com.demo.kalaha.model.Game;
import com.demo.kalaha.model.GameStatus;
import com.demo.kalaha.model.Pit;
import lombok.extern.slf4j.Slf4j;

/**
 * This class is responsible to check the last stone placing rules.
 *
 * @author vaishnavi
 */
@Slf4j
public class EndPitRule extends KalahaRule {

    @Override
    public void apply(Game game, Pit endPit) {
        log.debug("checking end rule for the last pit {}", endPit);

        lastEmptyPitRule(game, endPit);
        nextPlayerTurnRule(game, endPit);
        this.next.apply(game, endPit);
    }

    /**
     * Checks if the specified end pit:
     * - Is not a house,
     * - Belongs to the current player's side,
     * - Contains only one stone,
     * - And the opposite pit has stones,
     *
     * Then transfer the stones from the opposite pit and the current pit to the player's house.
     *
     * @param game The Kalaha game instance.
     * @param endPit The end pit to be checked.
     */
    private void lastEmptyPitRule(Game game, Pit endPit){

        if (!endPit.isHouse() && endPit.isPlayerPit(game.getGameStatus()) && endPit.getStoneCount().equals(1) ){
            Pit oppositePit = game.getBoard().getOppositePit(endPit);
            if (oppositePit.getStoneCount() > 0) {
                Pit house = game.getBoard().getPlayerHouse(endPit.getPlayerIndex());
                house.setStoneCount((house.getStoneCount() + oppositePit.getStoneCount()) + endPit.getStoneCount());
                oppositePit.setStoneCount(0);
                endPit.setStoneCount(0);
            }
        }
    }

    /**
     * Checks if it is the current or the next player's turn
     *
     * @param game The Kalaha game instance.
     * @param endPit The end pit to be checked.
     */
    private void nextPlayerTurnRule(Game game, Pit endPit){

        if(endPit.isPlayer1House() && game.getGameStatus().equals(GameStatus.PLAYER1_TURN)){
            game.setGameStatus(GameStatus.PLAYER1_TURN);
        }
        else if(endPit.isPlayer2House() && game.getGameStatus().equals(GameStatus.PLAYER2_TURN)){
            game.setGameStatus(GameStatus.PLAYER2_TURN);
        }
        else{
            GameStatus changeStage = game.getGameStatus() == GameStatus.PLAYER1_TURN? GameStatus.PLAYER2_TURN : GameStatus.PLAYER1_TURN;
            game.setGameStatus(changeStage);
        }
    }
}
