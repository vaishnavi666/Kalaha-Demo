package com.demo.kalaha.service.rule;

import com.demo.kalaha.model.Game;
import com.demo.kalaha.model.Pit;
import lombok.extern.slf4j.Slf4j;

/**
 * This class is responsible for distributing stones to the next pits
 * except for the opponent's house.
 *
 * @author vaishnavi
 */
@Slf4j
public class DistributePitStoneRule extends KalahaRule {

    @Override
    public void apply(Game game, Pit currentPit) {
        log.debug("check the rules for distributing stone to the next pit(s)");

        Integer stoneToDistribute = currentPit.getStoneCount();
        currentPit.setStoneCount(0);

        for (int i = 0; i < stoneToDistribute; i++) {
            currentPit = game.getBoard().getNextPit(currentPit);
            log.debug("next pit {}", currentPit);
            if (currentPit.canDistribute(game.getGameStatus())) {
                currentPit.setStoneCount(currentPit.getStoneCount() + 1);
            }else{
                i--;
            }
        }

        this.next.apply(game, currentPit);
    }
}
