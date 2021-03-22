package ca.school.battleship.packet.handler;

import ca.school.battleship.packet.JsonMessage;
import ca.school.battleship.user.User;
import ca.school.battleship.user.UserManager;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class ServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        UserManager.get().getUsers().put(ctx.channel().id().asLongText(), new User(ctx.channel().id().asLongText(), ctx));
        super.channelActive(ctx);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        UserManager.get().getUsers().remove(ctx.channel().id().asLongText());
        super.channelInactive(ctx);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        System.out.println("Receving content....");
        JsonMessage jsonMessage = (JsonMessage) msg;
        System.out.println("content: " + jsonMessage);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("Cause: " + cause.getMessage());
        super.exceptionCaught(ctx, cause);
    }

}