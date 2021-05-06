package ca.school.battleship.user.board;

import ca.school.battleship.game.boat.Boat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
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

    private transient Boat boat;

    public Boat getBoat() {
        if (this.boat != null) return this.boat;
        return this.boat = new Boat(this.boatName);
    }
}
