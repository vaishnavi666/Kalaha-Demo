package com.demo.kalaha.service.rule;

import com.demo.kalaha.model.Game;
import com.demo.kalaha.model.Pit;

public abstract class KalahaRule {

    protected KalahaRule next;
    public abstract void apply(Game game, Pit currentPit);

    public KalahaRule setNext(KalahaRule next) {
        this.next = next;
        return next;
    }

}
