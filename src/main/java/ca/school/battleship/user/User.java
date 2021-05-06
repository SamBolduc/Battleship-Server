package ca.school.battleship.user;

import ca.school.battleship.game.Game;
import ca.school.battleship.game.GameManager;
import ca.school.battleship.user.board.Board;
import io.netty.channel.ChannelHandlerContext;
import lombok.Getter;
import lombok.Setter;

@Getter
public class User {

    @Setter
    private String name;

    @Getter
    @Setter
    private Board board;

    @Setter
    private boolean ready;

    @Getter
    @Setter
    private boolean turn;

    private ChannelHandlerContext ctx;

    public User(ChannelHandlerContext ctx) {
        this("", ctx);
    }

    public User(String name, ChannelHandlerContext ctx) {
        this.name = name;
        this.ctx = ctx;
    }

    public String getId() {
        if (this.ctx == null) return "-1";

        return this.ctx.channel().id().asLongText();
    }

    public Game getGame() {
        return GameManager.get().getGame(this);
    }

    public void disconnect() {
        UserManager.get().getUsers().remove(this);
        Game game = this.getGame();
        if (game != null) {
            game.end(this);
        }
    }
}
