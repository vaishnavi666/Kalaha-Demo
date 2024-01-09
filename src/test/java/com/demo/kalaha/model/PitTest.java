package com.demo.kalaha.model;

import org.junit.Assert;
import org.junit.Test;

/**
 * Tests for the Kalaha pit functionality.
 */
public class PitTest {

    /**
     * Verifies the distributability of stones in a pit based on the game status.
     */
    @Test
    public void shouldDistributable() {
        // Given
        Pit pit1 = new Pit(1, 6, 1);
        Pit pit7 = new Pit(7, 6, 1);

        // Then
        Assert.assertEquals(true, pit1.canDistribute(GameStatus.PLAYER1_TURN));
        Assert.assertEquals(false, pit7.canDistribute(GameStatus.PLAYER2_TURN));
    }

    /**
     * Verifies if a pit belongs to the current player's side.
     */
    @Test
    public void shouldPlayerPit() {
        // Given
        Pit pit1 = new Pit(1, 6, 1);

        // Then
        Assert.assertEquals(true, pit1.isPlayerPit(GameStatus.PLAYER1_TURN));
        Assert.assertEquals(false, pit1.isPlayerPit(GameStatus.PLAYER2_TURN));
    }

    /**
     * Verifies if a pit is a house.
     */
    @Test
    public void shoudHouse() {
        // Given
        Pit pit1 = new Pit(1, 6, 1);
        Pit pit7 = new Pit(7, 6, 1);
        Pit pit13 = new Pit(13, 6, 1);
        Pit pit14 = new Pit(14, 6, 1);

        // Then
        Assert.assertEquals(false, pit1.isHouse());
        Assert.assertEquals(true, pit7.isHouse());
        Assert.assertEquals(false, pit13.isHouse());
        Assert.assertEquals(true, pit14.isHouse());
    }

    /**
     * Verifies if a pit is Player 1's house.
     */
    @Test
    public void shouldPlayer1House() {
        // Given
        Pit pit1 = new Pit(1, 6, 1);
        Pit pit7 = new Pit(7, 6, 1);
        Pit pit8 = new Pit(8, 6, 2);
        Pit pit14 = new Pit(14, 6, 2);

        // Then
        Assert.assertEquals(false, pit1.isPlayer1House());
        Assert.assertEquals(true, pit7.isPlayer1House());
        Assert.assertEquals(false, pit8.isPlayer1House());
        Assert.assertEquals(false, pit14.isPlayer1House());
    }

    /**
     * Verifies if a pit is Player 2's house.
     */
    @Test
    public void shouldPlayer2House() {
        // Given
        Pit pit1 = new Pit(1, 6, 1);
        Pit pit7 = new Pit(7, 6, 1);
        Pit pit8 = new Pit(8, 6, 2);
        Pit pit14 = new Pit(14, 6, 2);

        // Then
        Assert.assertEquals(false, pit1.isPlayer2House());
        Assert.assertEquals(false, pit7.isPlayer2House());
        Assert.assertEquals(false, pit8.isPlayer2House());
        Assert.assertEquals(true, pit14.isPlayer2House());
    }
}
