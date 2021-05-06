package ca.school.battleship.game;

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

    @Getter
    private List<User> queue = Lists.newArrayList();

    public GameManager() {
        instance = this;
    }

    public Game getGame(User user) {
        return this.games.stream().filter(el -> el.getPlayer1().equals(user) || el.getPlayer2().equals(user)).findFirst().orElse(null);
    }

    public boolean hasGame(User user) {
        return this.getGame(user) != null;
    }

    public void addToQueue(User user) {
        if (hasGame(user)) return;
        if (queue.contains(user)) return;
        queue.add(user);
        this.linkWaitingQueue();
    }

    public void linkWaitingQueue() {
        if (this.queue.size() <= 1) return;

        User player1 = this.queue.remove(0);
        User player2 = this.queue.remove(0);

        Game game = new Game(player1, player2);
        this.games.add(game);
    }

}
