package com.demo.kalaha.repository;


import com.demo.kalaha.model.Game;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Tests for the Game Memory Repository functionality.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class GameMemoryRepositoryTest {

    @Autowired
    private GameMemoryRepository gameMemoryRepository;

    /**
     * Verifies the creation and retrieval of a game from the Game Memory Repository.
     */
    @Test
    public void shouldCreateGame() {
        // Given
        Game game1 = gameMemoryRepository.create(6);

        // When
        Game game = gameMemoryRepository.findById(game1.getId());

        // Assert
        Assert.assertNotNull(game);
        Assert.assertEquals(game1, game1);
    }
}

