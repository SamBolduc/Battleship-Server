package ca.school.battleship.user;

import com.google.common.collect.Maps;
import lombok.Getter;

import java.util.Map;

public class UserManager {

    private static UserManager instance;

    public static UserManager get() {
        return instance;
    }

    @Getter
    private Map<String, User> users = Maps.newHashMap();

    public UserManager() {
        instance = this;
    }
}
