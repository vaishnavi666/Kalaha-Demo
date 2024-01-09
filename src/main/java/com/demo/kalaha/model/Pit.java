package com.demo.kalaha.model;

import lombok.*;

import javax.validation.constraints.NotNull;

/**
 * This class represent the pit of the KalahaConstants.
 *
 * @author vaishnavi
 */

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Pit {

    @NotNull
    private Integer pitIndex;

    @NotNull
    private Integer stoneCount;

    @NotNull
    private Integer playerIndex;
    /**
     * This method is use to determine different between pit & player house.
     *
     * @return Boolean false if player1 with house2 Or player 2 with house1, otherwise true
     */
    public Boolean canDistribute(GameStatus gameStatus){

        return !((gameStatus.equals(GameStatus.PLAYER1_TURN) && this.pitIndex.equals(KalahaConstants.PLAYER2_HOUSE))
                || (gameStatus.equals(GameStatus.PLAYER2_TURN) && this.pitIndex.equals(KalahaConstants.PLAYER1_HOUSE)));

    }


    /**
     * This method is use to determine the ownership of current player.
     * Player1 owns pit index from 1-7. Player 2 owns pit index from 8-14.
     *
     * @param gameStatus current game state. In this case player turn
     * @return True if current player is the owner of this pit otherwise false.
     */
    public Boolean isPlayerPit(GameStatus gameStatus){

        if(gameStatus.equals(GameStatus.PLAYER1_TURN) && this.playerIndex.equals(KalahaConstants.PLAYER1_INDEX)) {
            return true;
        }else if(gameStatus.equals(GameStatus.PLAYER2_TURN) && this.playerIndex.equals(KalahaConstants.PLAYER2_INDEX)) {
            return true;
        }

        return false;
    }


    /**
     * This method determine that if this pit is use as Pit or as House.
     *  Pit index 7 & 14 is using as House
     *
     * @return True is pit uses as house otherwise false.
     */
    public Boolean isHouse(){
        return this.pitIndex.equals(KalahaConstants.PLAYER1_HOUSE) || this.pitIndex.equals(KalahaConstants.PLAYER2_HOUSE);
    }


    /**
     * Determine the pit as player1 house.
     * @return true if player1 house
     */
    public Boolean isPlayer1House(){
        return this.playerIndex.equals(KalahaConstants.PLAYER1_INDEX) && this.pitIndex.equals(KalahaConstants.PLAYER1_HOUSE);

    }

    /**
     * Determine the pit as player2 house.
     * @return true if player2 house
     */
    public Boolean isPlayer2House(){
        return this.playerIndex.equals(KalahaConstants.PLAYER2_INDEX) && this.pitIndex.equals(KalahaConstants.PLAYER2_HOUSE);
    }

}
