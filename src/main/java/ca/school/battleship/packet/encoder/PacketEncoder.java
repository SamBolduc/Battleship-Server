package ca.school.battleship.packet.encoder;

import ca.school.battleship.Server;
import ca.school.battleship.packet.GenericPacket;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

import java.nio.charset.StandardCharsets;

public class PacketEncoder extends MessageToByteEncoder<GenericPacket> {

    @Override
    protected void encode(ChannelHandlerContext ctx, GenericPacket msg, ByteBuf out) {
        String content = Server.get().getGson().toJson(msg);

        out.writeInt(msg.getId());
        out.writeBytes(content.getBytes(StandardCharsets.UTF_8));
    }
}
