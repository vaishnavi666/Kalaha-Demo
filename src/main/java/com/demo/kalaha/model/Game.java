package com.demo.kalaha.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


/**
 * This class represent the game. A game contain players, board, games status and updateTime time.
 *
 * @author vaishnavi
 */

@Setter
@Getter
@NoArgsConstructor
@ToString
public class Game{

    private String id;
    private Board board;
    private Player player1;
    private Player player2;
    private Player winner;
    private GameStatus gameStatus;
    private Long updateAt;

    public Game(Integer initialStoneOnPit) {
        this.player1 = new Player("player1",KalahaConstants.PLAYER1_INDEX);
        this.player2 = new Player("player2",KalahaConstants.PLAYER2_INDEX);
        this.board = new Board(initialStoneOnPit, player1, player2);
        this.gameStatus = GameStatus.INIT;
        this.updateAt = System.currentTimeMillis();
    }

    /**
     * This method is set the time of the last activity.
     */
    public void updateTime(){

        this.setUpdateAt(System.currentTimeMillis());
    }

}
