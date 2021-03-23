package ca.school.battleship.game.packet;

import ca.school.battleship.packet.GenericPacket;
import io.netty.channel.ChannelHandlerContext;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@NoArgsConstructor
public class PlayPacket extends GenericPacket {

    @Override
    public void read(ChannelHandlerContext ctx) {

    }
}
