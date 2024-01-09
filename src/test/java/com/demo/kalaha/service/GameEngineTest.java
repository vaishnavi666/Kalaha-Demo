package com.demo.kalaha.service;

import com.demo.kalaha.exception.KalahaIllegalMoveException;
import com.demo.kalaha.model.*;
import com.demo.kalaha.exception.KalahaIllegalMoveException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Test class for verifying the functionality of the GameEngine.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class GameEngineTest {

    @Autowired
    private GameEngine gameEngine;

    /**
     * Verifies that the game starts with stones distributed from the player's own pit.
     */
    @Test
    public void shouldStartWithOwnPit(){

        //given
        Game game = new Game(6);

        //when
        gameEngine.play(game, game.getBoard().getPitByPitIndex(1));

        //then
        Assert.assertEquals(Integer.valueOf(0), game.getBoard().getStoneCountByPitIndex(1));
        Assert.assertEquals(Integer.valueOf(7), game.getBoard().getStoneCountByPitIndex(2) );
        Assert.assertEquals(Integer.valueOf(7), game.getBoard().getStoneCountByPitIndex(3));
        Assert.assertEquals(Integer.valueOf(7), game.getBoard().getStoneCountByPitIndex(4));
        Assert.assertEquals(Integer.valueOf(7), game.getBoard().getStoneCountByPitIndex(5));
        Assert.assertEquals(Integer.valueOf(7), game.getBoard().getStoneCountByPitIndex(6));
        Assert.assertEquals(Integer.valueOf(1), game.getBoard().getStoneCountByPitIndex(7));
        Assert.assertEquals(GameStatus.PLAYER1_TURN, game.getGameStatus());
        Assert.assertEquals(Integer.valueOf(1), game.getBoard().getPits().get(KalahaConstants.PLAYER1_HOUSE).getStoneCount());
        Assert.assertEquals(Integer.valueOf(0), game.getBoard().getPits().get(KalahaConstants.PLAYER2_HOUSE).getStoneCount());

    }

    /**
     * Verifies that an exception is thrown if starting with an empty pit.
     */
    @Test(expected = KalahaIllegalMoveException.class)
    public void shouldNotStartWithEmptyPit(){
        //given
        Game game = new Game(6);
        Pit pit = game.getBoard().getPits().get(2);
        pit.setStoneCount(0);

        //when
        gameEngine.play(game, game.getBoard().getPitByPitIndex(2));

    }

    /**
     * Verifies that an exception is thrown if starting with an opponent's pit.
     */
    @Test(expected = KalahaIllegalMoveException.class)
    public void shouldNotStartWithOpponentPit(){
        //given
        Game game = new Game(6);
        game.setGameStatus(GameStatus.PLAYER2_TURN);

        //when
        gameEngine.play(game, game.getBoard().getPitByPitIndex(2));
    }

    /**
     * Verifies the proper distribution of stones from Player 2's pit to Player 1's pit.
     */
    @Test
    public void shouldDistributeStoneFromPlayer2PitToPlayer1Pit() {
        //given
        Game game = new Game(6);

        //when
        gameEngine.play(game, game.getBoard().getPitByPitIndex(12));

        //then
        Assert.assertEquals(Integer.valueOf(0), game.getBoard().getStoneCountByPitIndex(12));
        Assert.assertEquals(Integer.valueOf(7), game.getBoard().getStoneCountByPitIndex(13));
        Assert.assertEquals(Integer.valueOf(1), game.getBoard().getStoneCountByPitIndex(14));
        Assert.assertEquals(Integer.valueOf(7), game.getBoard().getStoneCountByPitIndex(1));
        Assert.assertEquals(Integer.valueOf(7), game.getBoard().getStoneCountByPitIndex(2));
        Assert.assertEquals(Integer.valueOf(7), game.getBoard().getStoneCountByPitIndex(3));
        Assert.assertEquals(Integer.valueOf(7), game.getBoard().getStoneCountByPitIndex(4));
        Assert.assertEquals(GameStatus.PLAYER1_TURN, game.getGameStatus());
        Assert.assertEquals(Integer.valueOf(0), game.getBoard().getPits().get(KalahaConstants.PLAYER1_HOUSE).getStoneCount());
        Assert.assertEquals(Integer.valueOf(1), game.getBoard().getPits().get(KalahaConstants.PLAYER2_HOUSE).getStoneCount());
    }

    /**
     * Verifies the proper distribution of stones from Player 1's pit to Player 2's pit.
     */
    @Test
    public void shouldDistributeStoneFromPlayer1PitToPlayer2Pit(){
        //given
        Game game = new Game(6);

        //when
        gameEngine.play(game, game.getBoard().getPitByPitIndex(4));

        //then
        Assert.assertEquals(Integer.valueOf(0), game.getBoard().getStoneCountByPitIndex(4));
        Assert.assertEquals(Integer.valueOf(7), game.getBoard().getStoneCountByPitIndex(5));
        Assert.assertEquals(Integer.valueOf(7), game.getBoard().getStoneCountByPitIndex(6));
        Assert.assertEquals(Integer.valueOf(1), game.getBoard().getStoneCountByPitIndex(7));
        Assert.assertEquals(Integer.valueOf(7), game.getBoard().getStoneCountByPitIndex(8));
        Assert.assertEquals(Integer.valueOf(7), game.getBoard().getStoneCountByPitIndex(9));
        Assert.assertEquals(Integer.valueOf(7), game.getBoard().getStoneCountByPitIndex(10));
        Assert.assertEquals(GameStatus.PLAYER2_TURN, game.getGameStatus());
        Assert.assertEquals(Integer.valueOf(1), game.getBoard().getPits().get(KalahaConstants.PLAYER1_HOUSE).getStoneCount());
        Assert.assertEquals(Integer.valueOf(0), game.getBoard().getPits().get(KalahaConstants.PLAYER2_HOUSE).getStoneCount());
    }

    /**
     * Verifies the distribution of 13 stones.
     */
    @Test
    public void shouldDistribute13Stone(){
        //given
        Game game = new Game(6);
        game.getBoard().getPits().get(4).setStoneCount(13);
        game.getBoard().getPits().get(10).setStoneCount(10);

        //when
        gameEngine.play(game, game.getBoard().getPitByPitIndex(4));

        //then
        Assert.assertEquals(Integer.valueOf(0), game.getBoard().getStoneCountByPitIndex(4));
        Assert.assertEquals(Integer.valueOf(7), game.getBoard().getStoneCountByPitIndex(5));
        Assert.assertEquals(Integer.valueOf(7), game.getBoard().getStoneCountByPitIndex(6));
        Assert.assertEquals(Integer.valueOf(13), game.getBoard().getStoneCountByPitIndex(7));
        Assert.assertEquals(Integer.valueOf(7), game.getBoard().getStoneCountByPitIndex(8));
        Assert.assertEquals(Integer.valueOf(7), game.getBoard().getStoneCountByPitIndex(9));
        Assert.assertEquals(Integer.valueOf(0), game.getBoard().getStoneCountByPitIndex(10));
        Assert.assertEquals(GameStatus.PLAYER2_TURN, game.getGameStatus());
        Assert.assertEquals(Integer.valueOf(13), game.getBoard().getPits().get(KalahaConstants.PLAYER1_HOUSE).getStoneCount());
        Assert.assertEquals(Integer.valueOf(0), game.getBoard().getPits().get(KalahaConstants.PLAYER2_HOUSE).getStoneCount());
    }

    /**
     * Verifies that the house stone increases when playing on an own empty pit.
     */
    @Test
    public void shouldIncreaseHouseStoneOnOwnEmptyPit() {
        //given
        Game game = new Game(6);
        Pit pit1 = game.getBoard().getPitByPitIndex(1);
        pit1.setStoneCount(2);

        Pit pit2 = game.getBoard().getPitByPitIndex(3);
        pit2.setStoneCount(0);

        //when
        gameEngine.play(game, game.getBoard().getPitByPitIndex(1));

        //then
        Assert.assertEquals(Integer.valueOf(0), game.getBoard().getStoneCountByPitIndex(1));
        Assert.assertEquals(Integer.valueOf(0), game.getBoard().getStoneCountByPitIndex(3) );
        Assert.assertEquals(Integer.valueOf(0), game.getBoard().getStoneCountByPitIndex(11));
        Assert.assertEquals(GameStatus.PLAYER2_TURN, game.getGameStatus());
        Assert.assertEquals(Integer.valueOf(7), game.getBoard().getPits().get(KalahaConstants.PLAYER1_HOUSE).getStoneCount());
        Assert.assertEquals(Integer.valueOf(0), game.getBoard().getPits().get(KalahaConstants.PLAYER2_HOUSE).getStoneCount());
    }

    /**
     * Verifies that the game changes to Player 1's turn.
     */
    @Test
    public void shouldChangeGameToPlayerTurn1() {
        //given
        Game game = new Game(6);

        //when
        gameEngine.play(game, game.getBoard().getPitByPitIndex(1));

        //then
        Assert.assertEquals(GameStatus.PLAYER1_TURN, game.getGameStatus());
    }

    /**
     * Verifies that the game changes to Player 2's turn.
     */
    @Test
    public void shouldChangeGameToPlayerTurn2() {
        //given
        Game game = new Game(6);

        //when
        gameEngine.play(game, game.getBoard().getPitByPitIndex(2));

        //then
        Assert.assertEquals(GameStatus.PLAYER2_TURN, game.getGameStatus());
    }

    /**
     * Verifies that the game changes to Player 2's turn again.
     */
    @Test
    public void shouldChangeGameToPlayerTurn2Again() {

        //given
        Game game = new Game(6);

        //when
        Pit pit = game.getBoard().getPits().get(8);
        pit.setStoneCount(6);
        gameEngine.play(game, game.getBoard().getPitByPitIndex(8));

        //then
        Assert.assertEquals(GameStatus.PLAYER2_TURN, game.getGameStatus());
    }

    /**
     * Verifies that the game ends when all pits are empty on one side.
     */
    @Test
    public void shouldGameOver() {

        //given
        Game game = new Game(6);
        for(Integer key : game.getBoard().getPits().keySet()){
            Pit pit = game.getBoard().getPits().get(key);
            if(!pit.isHouse()) {
                pit.setStoneCount(0);
            }
        }
        game.getBoard().getPits().get(6).setStoneCount(1);

        //when
        gameEngine.play(game, game.getBoard().getPitByPitIndex(6));

        //then
        Assert.assertEquals(GameStatus.FINISHED, game.getGameStatus());
        Assert.assertEquals(game.getWinner(), game.getPlayer1());
    }

    /**
     * Verifies that Player 1 wins the game.
     */
    @Test
    public void shouldPlayer1Win() {

        //given
        Game game = new Game(6);
        for(Integer key : game.getBoard().getPits().keySet()){
            Pit pit = game.getBoard().getPits().get(key);
            if(!pit.isHouse()) {
                pit.setStoneCount(0);
            }
        }
        Pit lastPit = game.getBoard().getPits().get(6);
        lastPit.setStoneCount(1);

        //when
        gameEngine.play(game, game.getBoard().getPitByPitIndex(6));

        //then
        Assert.assertEquals(GameStatus.FINISHED, game.getGameStatus());
        Assert.assertEquals(game.getWinner(), game.getPlayer1());
    }

    /**
     * Verifies that Player 2 wins the game.
     */
    @Test
    public void shouldPlayer2Win(){

        //given
        Game game = new Game(6);
        for(Integer key : game.getBoard().getPits().keySet()){
            Pit pit = game.getBoard().getPits().get(key);
            if(!pit.isHouse()) {
                pit.setStoneCount(0);
            }
        }
        game.getBoard().getPits().get(13).setStoneCount(1);

        //when
        gameEngine.play(game, game.getBoard().getPitByPitIndex(13));

        //then
        Assert.assertEquals(GameStatus.FINISHED, game.getGameStatus());
        Assert.assertEquals(game.getWinner(), game.getPlayer2());
    }

    /**
     * Verifies that the game results in a draw.
     */
    @Test
    public void shouldDraw(){

        //given
        Game game = new Game(6);
        for(Integer key : game.getBoard().getPits().keySet()){
            Pit pit = game.getBoard().getPits().get(key);
            if(!pit.isHouse()) {
                pit.setStoneCount(0);
            }
        }
        game.getBoard().getPits().get(6).setStoneCount(1);
        game.getBoard().getPits().get(14).setStoneCount(1);

        //when
        gameEngine.play(game, game.getBoard().getPitByPitIndex(6));

        //then
        Assert.assertEquals(GameStatus.FINISHED, game.getGameStatus());
        Assert.assertEquals(game.getWinner(), null);
    }

}


