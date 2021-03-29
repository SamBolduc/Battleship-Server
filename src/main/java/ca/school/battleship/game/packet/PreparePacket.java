package ca.school.battleship.game.packet;

import ca.school.battleship.game.Game;
import ca.school.battleship.packet.GenericPacket;
import ca.school.battleship.user.User;
import ca.school.battleship.user.UserManager;
import io.netty.channel.ChannelHandlerContext;

public class PreparePacket extends GenericPacket {

    @Override
    public void read(ChannelHandlerContext ctx) {
        User user = UserManager.get().byId(ctx);
        if (user == null) return;

        Game game = user.getGame();
        if (game == null) return;

        user.setReady(true);

        if (game.allReady()) {
            game.start();
        }
    }
}
