package ca.school.battleship.game.packet;

import ca.school.battleship.packet.GenericPacket;
import io.netty.channel.ChannelHandlerContext;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
public class PlayPacket extends GenericPacket {

    @Setter
    private String username;

    @Override
    public void read(ChannelHandlerContext ctx) {
        System.out.println("Reading PlayPacket !");
        System.out.println("Username is: " + this.username);
    }
}
