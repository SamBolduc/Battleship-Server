package ca.school.battleship.game.packet;

import ca.school.battleship.game.Game;
import ca.school.battleship.packet.GenericPacket;
import ca.school.battleship.user.User;
import ca.school.battleship.user.UserManager;
import io.netty.channel.ChannelHandlerContext;

public class PlayerLeftPacket extends GenericPacket {
    @Override
    public void read(ChannelHandlerContext ctx) {
        User quitter = UserManager.get().getOrMake(ctx);
        Game game = quitter.getGame();
        if(game != null) {
            game.end(quitter);
        }
    }
}
