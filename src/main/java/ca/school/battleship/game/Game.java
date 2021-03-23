package ca.school.battleship.game;

import ca.school.battleship.user.User;
import lombok.Getter;

@Getter
public class Game {

    private User attacker;
    private User opponent;

    private User turn;

    private long startSecond;

    public Game() {

    }

    public void prepare() {
        //TODO: When player must set their boats, 1 minute cooldown then start the game.
    }

    public void start() {
        //TODO: Start the game, random first player
    }

    public void end() {
        //TODO: Stop the game
    }
}
