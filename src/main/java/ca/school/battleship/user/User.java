package ca.school.battleship.user;

import io.netty.channel.ChannelHandlerContext;

public class User {
    private String channelId;
    private ChannelHandlerContext ctx;

    public User(String channelId, ChannelHandlerContext ctx) {
        this.channelId = channelId;
        this.ctx = ctx;
    }
}
