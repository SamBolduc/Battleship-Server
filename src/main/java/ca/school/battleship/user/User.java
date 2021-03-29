package ca.school.battleship.user;

import ca.school.battleship.game.Game;
import ca.school.battleship.game.GameManager;
import io.netty.channel.ChannelHandlerContext;
import lombok.Getter;
import lombok.Setter;

@Getter
public class User {

    @Setter
    private String name;

    @Setter
    private boolean ready;

    private ChannelHandlerContext ctx;

    public User(ChannelHandlerContext ctx) {
        this("", ctx);
    }

    public User(String name, ChannelHandlerContext ctx) {
        this.name = name;
        this.ctx = ctx;
    }

    public Game getGame() {
        return GameManager.get().getGame(this);
    }

    public void disconnect() {
        Game game = this.getGame();

    }
}
