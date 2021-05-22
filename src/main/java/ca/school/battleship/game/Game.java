package ca.school.battleship.game;

import ca.school.battleship.game.boat.Boat;
import ca.school.battleship.game.packet.*;
import ca.school.battleship.user.User;
import ca.school.battleship.user.board.BoatPosition;
import com.google.common.collect.Lists;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
public class Game {

    @Setter
    private User player1;
    @Getter
    private User player2;

    private GameState state;

    public Game(User player1, User player2) {
        this.player1 = player1;
        this.player2 = player2;
        this.state = GameState.PLACING_BOAT;
        this.notifyPlayers();
        this.switchTurn();
    }

    private void notifyPlayers() {
        new OpponentFoundPacket().send(this.player1, this.player2);
    }

    public void start() {
        this.state = GameState.RUNNING;

        GameStartPacket packet = new GameStartPacket();
        this.player1.setTurn(true);

        packet.setTurn(true);
        packet.send(this.player1);

        packet.setTurn(false);
        packet.send(this.player2);
    }

    public void end(User looser) {
        User winner = this.player1 == looser ? this.player2 : this.player1;

        winner.setTurn(false);
        looser.setTurn(false);

        looser.setReady(false);
        winner.setReady(false);
        looser.setBoard(null);
        winner.setBoard(null);

        if (this.state.equals(GameState.RUNNING)) {
            new PlayerWinPacket().send(winner);
        } else {
            new PlayerLeftPacket().send(winner);
        }

        GameManager.get().getGames().remove(this);
    }

    public User getOpponent(User user) {
        return this.player1.equals(user) ? this.player2 : this.player1;
    }

    public boolean allReady() {
        return this.player1.isReady() && this.player2.isReady();
    }

    public void startIfReady() {
        if (this.getState().equals(GameState.PLACING_BOAT) && this.allReady()) this.start();
    }

    public int attack(User attacker, int maxDamage, float x, float y) {
        User opponent = this.getOpponent(attacker);

        return opponent.getBoard().attack(75, maxDamage, x, y);
    }

    public void switchTurn() {
        this.player1.setTurn(!this.player1.isTurn());
        this.player2.setTurn(!this.player1.isTurn());

        new TurnPacket(this.player1.isTurn()).send(this.player1);
        new TurnPacket(this.player2.isTurn()).send(this.player2);
    }

    private void sendBoatsStatus(User target) {
        BoatStatusPacket packet = new BoatStatusPacket();
        User opponent = this.getOpponent(target);

        List<Boat> myBoats = Lists.newArrayList();
        for (BoatPosition position : target.getBoard().getBoats()) {
            myBoats.add(position.getBoat());
        }

        List<Boat> enemyBoats = Lists.newArrayList();
        for (BoatPosition position : opponent.getBoard().getBoats()) {
            enemyBoats.add(position.getBoat());
        }

        packet.setMyBoats(myBoats);
        packet.setEnemyBoats(enemyBoats);
        packet.send(target);
    }

    public void sendBoatsStatus(User... users) {
        for (User user : users) {
            this.sendBoatsStatus(user);
        }
    }
}
