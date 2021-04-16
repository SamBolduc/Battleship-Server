package ca.school.battleship.game;

import ca.school.battleship.game.packet.GameStartPacket;
import ca.school.battleship.game.packet.OpponentFoundPacket;
import ca.school.battleship.user.User;
import lombok.Getter;
import lombok.Setter;

@Getter
public class Game {

    @Setter
    private User player1;
    @Getter
    private User player2;

    private User turn;

    private GameState state;

    public Game(User player1, User player2) {
        this.player1 = player1;
        this.player2 = player2;
        this.state = GameState.PLACING_BOAT;
        this.notifyPlayers();
    }

    private void notifyPlayers() {
        new OpponentFoundPacket().send(this.player1, this.player2);
    }

    public void start() {
        this.state = GameState.RUNNING;

        GameStartPacket packet = new GameStartPacket();
        this.turn = this.player1;

        packet.setTurn(true);
        packet.send(this.player1);

        packet.setTurn(false);
        packet.send(this.player2);
    }

    public void end(User looser) {
        User winner = this.player1 == looser ? this.player2 : this.player1;
        //TODO: Stop the game
    }

    public boolean allReady() {
        return this.player1.isReady() && this.player2.isReady();
    }

    public void startIfReady() {
        if(this.getState().equals(GameState.PLACING_BOAT) && this.allReady())
            this.start();
    }
}