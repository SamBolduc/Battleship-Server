package ca.school.battleship.game.packet;

import ca.school.battleship.game.GameManager;
import ca.school.battleship.packet.GenericPacket;
import ca.school.battleship.user.User;
import ca.school.battleship.user.UserManager;
import io.netty.channel.ChannelHandlerContext;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
public class PlayPacket extends GenericPacket {

    @Setter
    private String username;

    public PlayPacket(String username) {
        this.username = username;
    }

    @Override
    public void read(ChannelHandlerContext ctx) {
        User user = UserManager.get().byId(ctx);
        user.setName(this.username);
        GameManager.get().addToQueue(user);
    }

}
