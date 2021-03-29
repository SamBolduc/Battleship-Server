package ca.school.battleship.game;

import ca.school.battleship.game.packet.PlayPacket;
import ca.school.battleship.user.User;
import com.google.common.collect.Lists;
import lombok.Getter;

import java.util.List;

public class GameManager {

    private static GameManager instance;

    public static GameManager get() {
        return instance;
    }

    @Getter
    private List<Game> games = Lists.newArrayList();

    public GameManager() {
        instance = this;
    }

    public Game getGame(User user) {
        return this.games.stream().filter(el -> el.getAttacker().equals(user) || el.getOpponent().equals(user)).findFirst().orElse(null);
    }

    public boolean hasGame(User user) {
        return this.getGame(user) != null;
    }

    public Game findGame(User user) {
        Game game = this.games.stream().filter(g -> g.getState() == GameState.WAITING_OPPONENT).findFirst().orElse(null);

        if (game != null) {
            game.prepare(user);
        }

        if (game == null) {
            this.games.add(game = new Game(user));
            new PlayPacket(user.getName()).send(user); //Waiting for opponent
        }

        return game;
    }
}
