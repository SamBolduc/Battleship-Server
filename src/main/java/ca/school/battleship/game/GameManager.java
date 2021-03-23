package ca.school.battleship.game;

import ca.school.battleship.user.User;
import com.google.common.collect.Lists;

import java.util.List;

public class GameManager {

    private static GameManager instance;

    public static GameManager get() {
        return instance;
    }

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
}
