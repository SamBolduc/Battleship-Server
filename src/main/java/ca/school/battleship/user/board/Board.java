package ca.school.battleship.user.board;

import ca.school.battleship.game.AttackType;
import ca.school.battleship.game.boat.Boat;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
public class Board {

    @Getter
    private List<BoatPosition> boats;

    public int attack(int radius, int maxDamage, float x, float y) {
        if (this.boats == null || this.boats.isEmpty()) return -1;

        int ret = 0;
        for (BoatPosition position : this.boats) {
            if (position == null) continue;
            
            Boat boat = position.getBoat();
            if (boat.isDeath()) continue;

            double distance = boat.getDistance(position, x, y);

            if (distance == 0) { //In box
                boat.attack(maxDamage);
                if (maxDamage > ret) ret = maxDamage;
            } else if (distance - radius < 0) {
                int damage = (int) ((double) maxDamage * (1D - (distance / (double) radius)));
                boat.attack(damage);
                if (damage > ret) ret = damage;
            }
        }

        return ret;
    }

    private int getMaxDamage(AttackType type) {
        switch (type) {
            case SINGLE:
                return 33;
        }

        return 0;
    }
}
