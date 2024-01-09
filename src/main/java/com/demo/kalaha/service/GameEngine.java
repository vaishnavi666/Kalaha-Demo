package com.demo.kalaha.service;

import com.demo.kalaha.model.Pit;
import com.demo.kalaha.service.rule.*;
import com.demo.kalaha.model.Game;
import com.demo.kalaha.service.rule.*;
import org.springframework.stereotype.Component;

/**
 * This class represent the game rule chain.
 *
 * @author vaishnavi
 */
@Component
public class GameEngine {

    private final KalahaRule chain;
    public GameEngine() {

        this.chain = new StartPitRule();
        chain.setNext(new DistributePitStoneRule())
                .setNext(new EndPitRule())
                .setNext(new GameOver());
    }

    public void play(Game game, Pit pit) {

        this.chain.apply(game, pit);
    }

}
