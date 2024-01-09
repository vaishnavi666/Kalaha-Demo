package com.demo.kalaha.service;


import com.demo.kalaha.model.Game;

public interface GameService {

    Game initGame(Integer pitNumber);

    Game play(String gameId, Integer pitId);
}
