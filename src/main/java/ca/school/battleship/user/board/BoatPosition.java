package ca.school.battleship.user.board;

import ca.school.battleship.game.boat.Boat;
import lombok.Getter;
import lombok.Setter;

public class BoatPosition {

    @Getter
    @Setter
    private float x;
    @Getter
    @Setter
    private float y;
    @Getter
    @Setter
    private String boatName;

    public Boat getBoat() {
        return new Boat(this.boatName);
    }
}
