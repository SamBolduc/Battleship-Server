package ca.school.battleship.game;

import ca.school.battleship.game.packet.AttackPacket;
import ca.school.battleship.game.packet.PreparePacket;
import ca.school.battleship.user.User;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.Random;

@Getter
public class Game {

    private Random random = new Random();
    private User attacker;

    @Setter
    private User opponent;

    private User turn;

    private long startSecond;

    private GameState state;

    public Game(User attacker) {
        this.attacker = attacker;
        this.state = GameState.WAITING_OPPONENT;
    }

    public void tick() {
        if (this.state == GameState.PLACING_BOAT && Instant.now().getEpochSecond() > this.startSecond) {
            //TODO: Gen random boat positions
            this.start();
        }
    }

    public void prepare(User opponent) {
        this.opponent = opponent;
        this.state = GameState.PLACING_BOAT;
        this.startSecond = Instant.now().plusSeconds(60).getEpochSecond();

        new PreparePacket().send(this.attacker, this.opponent);
    }

    public void start() {
        this.state = GameState.RUNNING;
        this.turn = this.random.nextBoolean() ? this.attacker : this.opponent;

        new AttackPacket(this.turn.getCtx().channel().id().asLongText()).send(this.attacker, this.opponent);
    }

    public void end(User looser) {
        User winner = this.attacker == looser ? this.opponent : this.attacker;
        //TODO: Stop the game
    }

    public boolean allReady() {
        return this.attacker.isReady() && this.opponent.isReady();
    }
}
