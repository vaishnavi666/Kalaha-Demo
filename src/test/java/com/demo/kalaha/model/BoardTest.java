package com.demo.kalaha.model;

import org.junit.Assert;
import org.junit.Test;

/**
 * Tests for the Kalaha game board functionality.
 */
public class BoardTest {

    /**
     * Verifies the creation of the Kalaha game board.
     */
    @Test
    public void shouldCreateBoard() {
        // Given & When
        Board board = initBoard();

        // Then
        Assert.assertNotNull(board.getPits());
        Assert.assertEquals(14, board.getPits().size());
    }

    /**
     * Tests the retrieval of stone count based on pit index.
     */
    @Test
    public void shouldGetStoneCountByPitIndex() {
        // Given
        Board board = initBoard();

        // When
        Integer pit1Stone = board.getStoneCountByPitIndex(1);
        Integer house1Stone = board.getStoneCountByPitIndex(7);
        Integer pit8Stone = board.getStoneCountByPitIndex(8);
        Integer house2Stone = board.getStoneCountByPitIndex(14);

        // Then
        Assert.assertEquals(Integer.valueOf(6), pit1Stone);
        Assert.assertEquals(Integer.valueOf(0), house1Stone);
        Assert.assertEquals(Integer.valueOf(6), pit8Stone);
        Assert.assertEquals(Integer.valueOf(0), house2Stone);
    }

    /**
     * Tests the retrieval of a player's house.
     */
    @Test
    public void shouldGetPlayerHouse() {
        // Given
        Board board = initBoard();

        // When
        Pit house1 = board.getPlayerHouse(KalahaConstants.PLAYER1_INDEX);
        Pit house2 = board.getPlayerHouse(KalahaConstants.PLAYER2_INDEX);

        // Then
        Assert.assertEquals(Integer.valueOf(7), house1.getPitIndex());
        Assert.assertEquals(Integer.valueOf(14), house2.getPitIndex());
    }

    /**
     * Tests the retrieval of a pit by pit index.
     */
    @Test
    public void shouldGetPitByPitIndex() {
        // Given
        Board board = initBoard();

        // When
        Pit pit = board.getPitByPitIndex(2);

        // Then
        Assert.assertEquals(Integer.valueOf(2), pit.getPitIndex());
        Assert.assertEquals(Integer.valueOf(1), pit.getPlayerIndex());
    }

    /**
     * Tests the retrieval of the next pit.
     */
    @Test
    public void shouldGetNextPit() {
        // Given
        Board board = initBoard();

        // When
        Pit pit1 = board.getPitByPitIndex(1);
        Pit pit2 = board.getNextPit(pit1);
        Pit pit14 = board.getPitByPitIndex(14);
        Pit pit1Again = board.getNextPit(pit14);

        // Then
        Assert.assertEquals(Integer.valueOf(2), pit2.getPitIndex());
        Assert.assertEquals(pit1, pit1Again);
    }

    /**
     * Tests the retrieval of the opposite pit.
     */
    @Test
    public void shouldGetOppositePit() {
        // Given
        Board board = initBoard();

        // When
        Pit pit1 = board.getPitByPitIndex(1);
        Pit oppositePit = board.getOppositePit(pit1);
        Pit pit1Again = board.getOppositePit(oppositePit);

        // Then
        Assert.assertEquals(Integer.valueOf(13), oppositePit.getPitIndex());
        Assert.assertEquals(pit1, pit1Again);
    }

    /**
     * Tests the retrieval of player 1's pit stone count.
     */
    @Test
    public void shouldGetPlayer1PitStoneCount() {
        // Given
        Board board = initBoard();

        // When
        Integer player1PitStoneCount = board.getPlayer1PitStoneCount();

        // Then
        Assert.assertEquals(Integer.valueOf(36), player1PitStoneCount);
    }

    /**
     * Tests the retrieval of player 2's pit stone count.
     */
    @Test
    public void shouldGetPlayer2PitStoneCount() {
        // Given
        Board board = initBoard();

        // When
        board.getPits().get(8).setStoneCount(0);
        Integer player2PitStoneCount = board.getPlayer2PitStoneCount();

        // Then
        Assert.assertEquals(Integer.valueOf(30), player2PitStoneCount);
    }

    /**
     * Initializes a Kalaha game board with players.
     *
     * @return The initialized game board.
     */
    private static Board initBoard() {
        Player player1 = new Player("player1", KalahaConstants.PLAYER1_INDEX);
        Player player2 = new Player("player2", KalahaConstants.PLAYER2_INDEX);
        return new Board(KalahaConstants.INITIAL_STONE_ON_PIT, player1, player2);
    }
}

