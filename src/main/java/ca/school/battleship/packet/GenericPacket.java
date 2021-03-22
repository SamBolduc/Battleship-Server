package ca.school.battleship.packet;

import ca.school.battleship.Server;
import io.netty.channel.ChannelHandlerContext;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public abstract class GenericPacket<T> {

    private int id;

    public GenericPacket(int id) {
        this.id = id;
    }

    public abstract void read(T packet);

    public void send(ChannelHandlerContext channel, T packet) {
        channel.writeAndFlush(Server.get().getGson().toJson(packet));
    }
}
