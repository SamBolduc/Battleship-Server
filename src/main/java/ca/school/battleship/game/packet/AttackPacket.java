package ca.school.battleship.game.packet;

import ca.school.battleship.game.Game;
import ca.school.battleship.game.GameManager;
import ca.school.battleship.game.GameState;
import ca.school.battleship.packet.GenericPacket;
import ca.school.battleship.user.User;
import ca.school.battleship.user.UserManager;
import io.netty.channel.ChannelHandlerContext;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class AttackPacket extends GenericPacket {

    private float x;
    private float y;

    @Override
    public void read(ChannelHandlerContext ctx) {
        User user = UserManager.get().byId(ctx);

        Game game = GameManager.get().getGame(user);
        if (game == null) return;
        if (game.getState() != GameState.RUNNING) return;

        game.attack(user, this.x, this.y);
    }
}