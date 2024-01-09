package com.demo.kalaha.controller;

import com.demo.kalaha.exception.KalahaException;
import com.demo.kalaha.exception.KalahaIllegalMoveException;
import com.demo.kalaha.model.KalahaConstants;
import com.demo.kalaha.service.GameService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * The KalahaGameController class represents the controller for the Kalaha game.
 * It manages the game start and the stone move.
 */

@Slf4j
@RestController
@RequestMapping("/api/kalaha")
public class KalahaGameController {

    private final GameService gameService;
    public KalahaGameController(GameService gameService) {

        this.gameService = gameService;
    }


    @PostMapping(value = "/games")
    public ResponseEntity initBoard(@RequestParam(name = "stone", defaultValue = "6", required = false) Integer numberOfStone){
        log.debug("initializing kalaha game");
        return ResponseEntity.status(HttpStatus.CREATED).body(gameService.initGame(numberOfStone));
    }


    @PutMapping("/games/{gameId}/pits/{pitIndex}")
    public ResponseEntity play(@PathVariable String gameId, @PathVariable Integer pitIndex){
        log.debug("Moving stone from pit {}",pitIndex);

        if(pitIndex > KalahaConstants.PIT_END_INDEX || pitIndex < KalahaConstants.PIT_START_INDEX){
            throw new KalahaException("Incorrect pit index");
        }

        if(pitIndex.equals(KalahaConstants.PLAYER1_HOUSE) || pitIndex.equals(KalahaConstants.PLAYER2_HOUSE)){
            throw new KalahaIllegalMoveException("House stones are not allowed to be distributed");
        }

        return ResponseEntity.ok().body(gameService.play(gameId, pitIndex));
    }

}
