package ca.school.battleship.packet.decoder;

import ca.school.battleship.Server;
import ca.school.battleship.model.RequestData;
import ca.school.battleship.packet.GenericPacket;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class PacketDecoder extends ReplayingDecoder<RequestData> {

    private final Charset charset = StandardCharsets.UTF_8;

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) {
        System.out.println("New message from: " + ctx.channel().remoteAddress());

        int packetId = in.readInt();
        String content = in.toString(charset);

        GenericPacket<?> packet = Server.get().getGson().fromJson(in.toString(charset), Server.get().getPacketHandler().getClass(packetId));
        RequestData data = new RequestData();
        data.setIntValue(in.readInt());
        int strLen = in.readInt();
        data.setStringValue(in.readCharSequence(strLen, charset).toString());
        out.add(data);
    }
}