package com.demo.kalaha.model;

import org.junit.Assert;
import org.junit.Test;

/**
 * Tests for the Kalaha game functionality.
 */
public class GameTest {

    /**
     * Verifies the creation of a new Kalaha game.
     */
    @Test
    public void shouldCreateGame() {
        // Given
        Game game = new Game(KalahaConstants.INITIAL_STONE_ON_PIT);

        // Then
        Assert.assertEquals(KalahaConstants.PLAYER1_INDEX, game.getPlayer1().getPlayerIndex());
        Assert.assertEquals(KalahaConstants.PLAYER2_INDEX, game.getPlayer2().getPlayerIndex());
        Assert.assertNotNull(game.getBoard());
        Assert.assertEquals(14, game.getBoard().getPits().size());
        Assert.assertEquals(GameStatus.INIT, game.getGameStatus());
    }
}

