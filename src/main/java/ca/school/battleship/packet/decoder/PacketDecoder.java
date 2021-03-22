package ca.school.battleship.packet.decoder;

import ca.school.battleship.Server;
import ca.school.battleship.packet.JsonMessage;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.util.CharsetUtil;

import java.util.List;

public class PacketDecoder extends ByteToMessageDecoder {

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) {
        System.out.println("New message from: " + ctx.channel().remoteAddress());
        int len = in.readInt();
        String content = in.readCharSequence(len, CharsetUtil.UTF_8).toString();
        System.out.println("Content: " + content);
        JsonMessage jsonMessage = Server.get().getGson().fromJson(content, JsonMessage.class);
        System.out.println("Message: " + jsonMessage);
        out.add(jsonMessage);
    }
}