package ca.school.battleship.user;

import com.google.common.collect.Lists;
import io.netty.channel.ChannelHandlerContext;
import lombok.Getter;

import java.util.List;

public class UserManager {

    private static UserManager instance;

    public static UserManager get() {
        return instance;
    }

    @Getter
    private List<User> users = Lists.newArrayList();

    public UserManager() {
        instance = this;
    }

    public User getOrMake(ChannelHandlerContext ctx) {
        User user = this.byId(ctx);
        if (user == null) {
            user = new User(ctx);
            this.users.add(user);
        }
        return user;
    }

    public User byId(ChannelHandlerContext ctx) {
        return this.byId(ctx.channel().id().asLongText());
    }

    public User byId(String id) {
        return this.users.stream().filter(el -> el.getCtx().channel().id().asLongText().equalsIgnoreCase(id)).findFirst().orElse(null);
    }
}
