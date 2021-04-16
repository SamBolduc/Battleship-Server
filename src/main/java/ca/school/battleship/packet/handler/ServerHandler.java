package ca.school.battleship.packet.handler;

import ca.school.battleship.Server;
import ca.school.battleship.game.GameManager;
import ca.school.battleship.packet.GenericPacket;
import ca.school.battleship.packet.JsonMessage;
import ca.school.battleship.user.User;
import ca.school.battleship.user.UserManager;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class ServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        UserManager.get().getOrMake(ctx);
        super.channelActive(ctx);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        User user = UserManager.get().byId(ctx);
        if (user != null) {
            user.disconnect();
        }
        
        super.channelInactive(ctx);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        JsonMessage jsonMessage = (JsonMessage) msg;

        GenericPacket packet = Server.get().getGson().fromJson(jsonMessage.getContent(), Server.get().getPacketHandler().getClass(jsonMessage.getPacketId()));
        packet.setId(jsonMessage.getPacketId());
        packet.read(ctx);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("Exception: " + cause.getMessage());
        //        super.exceptionCaught(ctx, cause);
    }

}