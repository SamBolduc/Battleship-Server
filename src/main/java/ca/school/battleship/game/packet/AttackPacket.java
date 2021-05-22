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
        User target = UserManager.get().byId(ctx);

        Game game = GameManager.get().getGame(target);
        if (game == null) return;
        if (game.getState() != GameState.RUNNING) return;

        this.damageDealt = game.attack(target, 33, this.x, this.y);
        this.send(target);

        if (this.damageDealt > 0) {
            game.sendBoatsStatus(game.getPlayer1(), game.getPlayer2());
        }

        if (target.getBoard().allDeath()) {
            game.end(target);
            return;
        }


        game.switchTurn();
    }
}
