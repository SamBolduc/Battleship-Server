package ca.school.battleship.packet;

import ca.school.battleship.Server;
import io.netty.channel.ChannelHandlerContext;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@Data
@ToString
public abstract class GenericPacket {

    private int id;

    public abstract void read(ChannelHandlerContext ctx);

    public void send(ChannelHandlerContext ctx) {
        ctx.writeAndFlush(this.asJsonMessage());
    }

    private JsonMessage asJsonMessage() {
        JsonMessage msg = new JsonMessage();
        msg.setPacketId(this.id);
        msg.setContent(Server.get().getGson().toJson(this));

        return msg;
    }
}
