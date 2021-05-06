package ca.school.battleship.game.packet;

import ca.school.battleship.packet.GenericPacket;
import io.netty.channel.ChannelHandlerContext;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class TurnPacket extends GenericPacket {

    private boolean turn;

    @Override
    public void read(ChannelHandlerContext ctx) {
    }
}
