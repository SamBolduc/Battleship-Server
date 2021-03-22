package ca.school.battleship.handler;

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
        String content = (String) msg;
        
        new ActionHandler(ctx, requestData);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        //        super.exceptionCaught(ctx, cause);
        System.out.println("Cause: " + cause.getMessage());
    }

}