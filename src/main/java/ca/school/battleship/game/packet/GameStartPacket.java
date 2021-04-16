package ca.school.battleship.game.packet;

import ca.school.battleship.packet.GenericPacket;
import io.netty.channel.ChannelHandlerContext;
import lombok.Setter;

public class GameStartPacket extends GenericPacket {

    @Setter
    private boolean turn;

    @Override
    public void read(ChannelHandlerContext ctx) {

    }
}
