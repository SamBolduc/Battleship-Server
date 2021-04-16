package ca.school.battleship.game.packet;

import ca.school.battleship.packet.GenericPacket;
import io.netty.channel.ChannelHandlerContext;
import lombok.Getter;
import lombok.Setter;

public class OpponentFoundPacket extends GenericPacket {

    @Override
    public void read(ChannelHandlerContext ctx) {

    }

}
