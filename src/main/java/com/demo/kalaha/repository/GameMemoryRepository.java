package com.demo.kalaha.repository;

import com.demo.kalaha.exception.KalahaException;
import com.demo.kalaha.model.Game;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * This class represent the storage of the game where store the game object in a map & get the map by id.
 *
 * @author vaishnavi
 */

@Slf4j
@Component
public class GameMemoryRepository {

    private static final Map<String, Game> gameMap = new ConcurrentHashMap<>();

    /**
     * This method create new Game and save the object in a Map.
     *
     * @param initialPitStoneCount is the number of the stone of a pit.
     * @return Game object.
     */
    public Game create(Integer initialPitStoneCount){
        String id = UUID.randomUUID().toString();
        Game game = new Game(initialPitStoneCount);
        game.setId(id);
        gameMap.put(id, game);
        return gameMap.get(id);
    }

    /**
     * This method will return the game object by id.
     *
     * @param id is the game id.
     * @return Game
     */
    public Game findById(String id) {
        return Objects.requireNonNull(gameMap.get(id), "Game is not found for the id: " + id);
    }

}
