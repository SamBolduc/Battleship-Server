package ca.school.battleship.packet.decoder;

import ca.school.battleship.Server;
import ca.school.battleship.packet.JsonMessage;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.nio.charset.StandardCharsets;
import java.util.List;

public class PacketDecoder extends ByteToMessageDecoder {

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) {
        int len = in.readInt();
        String content = in.readCharSequence(len, StandardCharsets.UTF_8).toString();
        JsonMessage jsonMessage = Server.get().getGson().fromJson(content, JsonMessage.class);
        out.add(jsonMessage);
    }
}