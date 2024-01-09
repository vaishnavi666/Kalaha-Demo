package com.demo.kalaha.model;

import lombok.*;

import javax.validation.constraints.NotNull;

/**
 * This class represent the player of the game.
 *
 * @author vaishnavi
 */

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Player{

    @NotNull
    private String name;

    @NotNull
    private Integer playerIndex;

}
