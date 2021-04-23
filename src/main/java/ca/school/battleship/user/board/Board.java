package ca.school.battleship.user.board;

import ca.school.battleship.game.boat.Boat;
import lombok.Getter;

import java.util.List;

public class Board {

    @Getter
    private List<BoatPosition> boats;

    public int attack(int radius, float x, float y) {
        if (this.boats == null || this.boats.isEmpty()) return -1;

        for (BoatPosition position : this.boats) {
            Boat boat = position.getBoat();

            double distance = boat.getDistance(position, x, y);
            System.out.println("Distance: " + distance);
            if (distance == 0 || distance < radius) {
                System.out.println("Boat: " + boat.getName() + " got hit.");
            }
        }

        return 0;
    }
}
