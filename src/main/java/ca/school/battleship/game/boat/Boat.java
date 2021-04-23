package ca.school.battleship.game.boat;

import ca.school.battleship.user.board.BoatPosition;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Boat {

    private String name;

    public Boat(String boatName) {
        this.name = boatName;
    }

    public BoatSize getBoatSize() {
        return BoatSize.byName(this.name);
    }

    public double getDistance(BoatPosition position, float x, float y) {
        BoatSize boatSize = this.getBoatSize();

        /*
         * Check distance outside
         * */
        float dxo = Math.max(position.getX() - boatSize.getWidth() / 2 - x, x - position.getX() + boatSize.getWidth() / 2);
        float dyo = Math.max(position.getY() - boatSize.getHeight() / 2 - y, y - position.getY() + boatSize.getHeight() / 2);
        double hypo = Math.sqrt(Math.pow(dxo, 2) + Math.pow(dyo, 2));
        if (hypo > 0) return hypo;

        /*
         * Impact is inside.
         * */
        float dxi = Math.min(position.getX() + boatSize.getWidth() / 2 - x, x - position.getX() - boatSize.getWidth() / 2);
        float dyi = Math.min(position.getY() + boatSize.getHeight() / 2 - y, y - position.getY() - boatSize.getHeight() / 2);
        return Math.min(dxi, dyi);
    }

/*
    function distance(rect, p) {
        // outside
        var dxo = Math.max(rect.min.x - p.x, 0, p.x - rect.max.x);
        var dyo = Math.max(rect.min.y - p.y, 0, p.y - rect.max.y);
        var hypothenuse = Math.sqrt(dxo * dxo + dyo * dyo);

        // inside
        var dxi = Math.min(rect.max.x - p.x, p.x - rect.min.x);
        var dyi = Math.min(rect.max.y - p.y, p.y - rect.min.y);

        return hypothenuse > 0 ? hypothenuse : Math.min(dxi, dyi);
    }*/

    enum BoatSize {
        CARRIER(264, 124),
        BATTLESHIP(261, 67),
        CRUISER(193, 48),
        SUBMARINE(195, 48),
        DESTROYER(128, 32);

        @Getter
        private float width, height;

        BoatSize(float width, float height) {
            this.width = width;
            this.height = height;
        }

        public static BoatSize byName(String name) {
            for (BoatSize value : BoatSize.values()) {
                if (value.name().equalsIgnoreCase(name)) return value;
            }

            return null;
        }
    }
}
