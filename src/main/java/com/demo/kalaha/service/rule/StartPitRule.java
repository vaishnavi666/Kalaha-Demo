package com.demo.kalaha.service.rule;

import com.demo.kalaha.exception.KalahaIllegalMoveException;
import com.demo.kalaha.exception.KalahaIllegalMoveException;
import com.demo.kalaha.model.*;
import lombok.extern.slf4j.Slf4j;

/**
 * Checks and enforces the rules for a player's turn to start from a specific pit
 * in the Kalaha game.
 *
 * @author vaishnavi
 */
@Slf4j
public class StartPitRule extends KalahaRule {

    @Override
    public void apply(Game game, Pit startPit) {
        log.debug("check rules for the start pit {}", startPit);

        checkPlayerTurnRule(game, startPit);
        checkEmptyStartRULE(startPit);
        this.next.apply(game, startPit);
    }
    /**
     * Check if the game is in the initialization state and set the player's turn accordingly.
     *
     * @param game The Kalaha game instance.
     * @param startPit The pit from which the player intends to start the turn.
     * @throws KalahaIllegalMoveException If the chosen pit is not valid for the current player's turn.
     */
    private void checkPlayerTurnRule(Game game, Pit startPit){

        if(game.getGameStatus().equals(GameStatus.INIT)) {
            GameStatus gameStatus =  startPit.getPlayerIndex().equals(KalahaConstants.PLAYER1_INDEX) ? GameStatus.PLAYER1_TURN : GameStatus.PLAYER2_TURN;
            game.setGameStatus(gameStatus);
        }

        if((game.getGameStatus().equals(GameStatus.PLAYER1_TURN) && startPit.getPitIndex() >= KalahaConstants.PLAYER1_HOUSE) ||
                (game.getGameStatus().equals(GameStatus.PLAYER2_TURN) && startPit.getPitIndex() <= KalahaConstants.PLAYER1_HOUSE)){
            throw new KalahaIllegalMoveException("Incorrect pit to play");
        }
    }

    /**
     * Check if the player chose empty pit.
     *
     * @param startPit The pit from which the player intends to start the turn.
     * @throws KalahaIllegalMoveException If the chosen pit is not valid for the current player's turn.
     */
    private void checkEmptyStartRULE(Pit startPit){

        if(startPit.getStoneCount() == 0){
            throw new KalahaIllegalMoveException("Can not start from empty pit");
        }
    }
}
