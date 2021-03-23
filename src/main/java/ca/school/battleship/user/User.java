package ca.school.battleship.user;

import io.netty.channel.ChannelHandlerContext;
import lombok.Getter;

@Getter
public class User {

    private String name;
    private String channelId;
    private ChannelHandlerContext ctx;

    public User(String channelId, ChannelHandlerContext ctx) {
        this("", channelId, ctx);
    }

    public User(String name, String channelId, ChannelHandlerContext ctx) {
        this.name = name;
        this.channelId = channelId;
        this.ctx = ctx;
    }
}
