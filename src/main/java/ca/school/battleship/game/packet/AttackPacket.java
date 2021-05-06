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
    private int damageDealt;

    @Override
    public void read(ChannelHandlerContext ctx) {
        User user = UserManager.get().byId(ctx);

        Game game = GameManager.get().getGame(user);
        if (game == null) return;
        if (game.getState() != GameState.RUNNING) return;

        this.damageDealt = game.attack(user, 33, this.x, this.y);
        this.send(user);

        if (this.damageDealt > 0) {
            game.sendBoatsStatus(game.getPlayer1(), game.getPlayer2());
        }
    }
}
