package ca.school.battleship.game.packet;

import ca.school.battleship.game.boat.Boat;
import ca.school.battleship.packet.GenericPacket;
import io.netty.channel.ChannelHandlerContext;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class BoatStatusPacket extends GenericPacket {

    private List<Boat> myBoats;
    private List<Boat> enemyBoats;

    @Override
    public void read(ChannelHandlerContext ctx) {
    }
}
