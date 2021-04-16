package ca.school.battleship.game.packet;

import ca.school.battleship.game.Game;
import ca.school.battleship.game.GameManager;
import ca.school.battleship.packet.GenericPacket;
import ca.school.battleship.user.board.Board;
import ca.school.battleship.user.User;
import ca.school.battleship.user.UserManager;
import io.netty.channel.ChannelHandlerContext;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class BoatPositionPacket extends GenericPacket {

    private Board board;

    @Override
    public void read(ChannelHandlerContext ctx) {
        User user = UserManager.get().byId(ctx);
        user.setBoard(this.board);
        user.setReady(true);

        Game game = GameManager.get().getGame(user);
        if(game != null)
            game.startIfReady();
    }
}
