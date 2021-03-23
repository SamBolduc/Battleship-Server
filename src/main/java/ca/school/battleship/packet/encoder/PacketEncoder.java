package ca.school.battleship.packet.encoder;

import ca.school.battleship.packet.JsonMessage;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

import java.nio.charset.StandardCharsets;

public class PacketEncoder extends MessageToByteEncoder<JsonMessage> {

    @Override
    protected void encode(ChannelHandlerContext ctx, JsonMessage msg, ByteBuf out) throws Exception {
        byte[] bytes = msg.toJson().getBytes(StandardCharsets.UTF_8);
        out.writeInt(bytes.length);
        out.writeBytes(bytes);
    }
}
