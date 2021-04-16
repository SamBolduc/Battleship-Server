package ca.school.battleship.packet;

import ca.school.battleship.Server;
import ca.school.battleship.user.User;
import io.netty.channel.ChannelHandlerContext;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@Data
@ToString
public abstract class GenericPacket {

    private transient int id;

    public abstract void read(ChannelHandlerContext ctx);

    public void send(User... users) {
        for (User user : users) {
            this.send(user.getCtx());
        }
    }

    private void send(ChannelHandlerContext ctx) {
        this.setId(Server.get().getPacketHandler().getPacket(this.getClass()).getId());
        ctx.writeAndFlush(this.asJsonMessage());
    }

    private JsonMessage asJsonMessage() {
        JsonMessage msg = new JsonMessage();
        msg.setPacketId(this.id);
        msg.setContent(Server.get().getGson().toJson(this));

        return msg;
    }
}
