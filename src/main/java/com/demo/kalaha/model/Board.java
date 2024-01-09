package com.demo.kalaha.model;

import com.demo.kalaha.exception.KalahaException;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.IntStream;

/**
 * This class represents the board of the game. The board contains all the pits.
 *
 * @author vaishnavi
 */
@Setter
@Getter
@NoArgsConstructor
@ToString
public class Board {

    private Map<Integer, Pit> pits;

    public Board(Integer initialStoneOnPit, Player player1, Player player2) {
        this.pits = initPit(initialStoneOnPit, player1, player2);
    }

    private Map<Integer, Pit> initPit(Integer initialStoneOnPit, Player player1, Player player2) {
        Map<Integer, Pit> pits = new ConcurrentHashMap<>();

        IntStream.range(KalahaConstants.PIT_START_INDEX, KalahaConstants.PLAYER1_HOUSE)
                .forEach(i -> pits.put(i, new Pit(i, initialStoneOnPit, player1.getPlayerIndex())));

        pits.put(KalahaConstants.PLAYER1_HOUSE, new Pit(KalahaConstants.PLAYER1_HOUSE, KalahaConstants.INITIAL_STONE_ON_HOUSE, player1.getPlayerIndex()));

        IntStream.range(KalahaConstants.PLAYER1_HOUSE + 1, KalahaConstants.PLAYER2_HOUSE)
                .forEach(i -> pits.put(i, new Pit(i, initialStoneOnPit, player2.getPlayerIndex())));

        pits.put(KalahaConstants.PLAYER2_HOUSE, new Pit(KalahaConstants.PLAYER2_HOUSE, KalahaConstants.INITIAL_STONE_ON_HOUSE, player2.getPlayerIndex()));

        return pits;
    }
    /**
     * Get the number of stones in a specific pit.
     *
     * @param pitIndex Pit index.
     * @return Integer number of total stones on a pit
     */
    public Integer getStoneCountByPitIndex(Integer pitIndex) {

        return getPitByPitIndex(pitIndex).getStoneCount();
    }
    /**
     * Get the house of a specific player.
     *
     * @param playerIndex The index of the player (1 or 2).
     * @return The Pit representing the house of the specified player.
     * @throws KalahaException If the playerIndex is not correct.
     */
    public Pit getPlayerHouse(Integer playerIndex) {
        // Player 1's house
        if (playerIndex.equals(KalahaConstants.PLAYER1_INDEX)) {
            return pits.get(KalahaConstants.PLAYER1_HOUSE);
        }
        // Player 2's house
        else if (playerIndex.equals(KalahaConstants.PLAYER2_INDEX)) {
            return pits.get(KalahaConstants.PLAYER2_HOUSE);
        }

        // Throw an exception if the playerIndex is not correct
        throw new KalahaException("playerIndex is not correct");
    }

    /**
     * Get a pit by its index.
     *
     * @param pitIndex The index of the pit.
     * @return The Pit at the specified index.
     */
    public Pit getPitByPitIndex(Integer pitIndex) {

        return pits.get(pitIndex);
    }

    /**
     * Get the next pit in the sequence.
     *
     * @param pit The current Pit.
     * @return The next Pit in the sequence.
     */
    public Pit getNextPit(Pit pit) {
        Integer index = pit.getPitIndex() + 1;
        if(index > KalahaConstants.PLAYER2_HOUSE) {
            index = 1;
        }
        return pits.get(index);
    }

    /**
     * Get the opposite pit in the sequence.
     *
     * @param pit The current Pit.
     * @return The opposite Pit in the sequence.
     */
    public Pit getOppositePit(Pit pit) {

        return pits.get(KalahaConstants.PIT_END_INDEX - pit.getPitIndex());
    }

    /**
     * Get the total number of stones in all pits for Player 1.
     *
     * @return The total number of stones in all pits for Player 1.
     */
    public Integer getPlayer1PitStoneCount() {
        return IntStream.range(KalahaConstants.PIT_START_INDEX, KalahaConstants.PLAYER1_HOUSE)
                .mapToObj(i -> this.getPits().get(i))
                .mapToInt(Pit::getStoneCount)
                .sum();
    }

    /**
     * Get the total number of stones in all pits for Player 2.
     *
     * @return The total number of stones in all pits for Player 2.
     */
    public Integer getPlayer2PitStoneCount() {
        return IntStream.range(KalahaConstants.PLAYER1_HOUSE + 1, KalahaConstants.PLAYER2_HOUSE)
                .mapToObj(i -> this.getPits().get(i))
                .mapToInt(Pit::getStoneCount)
                .sum();
    }
}

