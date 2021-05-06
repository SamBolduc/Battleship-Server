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
    private int maxHealth;
    private int currentHealth;
    private transient BoatSize boatSize;

    public Boat(String boatName) {
        this.name = boatName;
        this.boatSize = BoatSize.byName(this.name);

        if (this.boatSize != null) {
            this.maxHealth = this.boatSize.getMaxHealth();
            this.currentHealth = this.boatSize.getMaxHealth();
        }
    }

    public void attack(int damage) {
        if (this.isDeath()) return;

        this.currentHealth -= damage;
    }

    public boolean isDeath() {
        return this.currentHealth <= 0;
    }

    private double HYPOT(double x, double y) {
        return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
    }

    public double getDistance(BoatPosition position, double x, double y) {
        BoatSize boatSize = this.getBoatSize();
        double splitWidth = boatSize.getWidth() / 2;
        double splitHeight = boatSize.getHeight() / 2;

        return this.getDistance(x, y, position.getX() - splitWidth, position.getY() - splitHeight, position.getX() + splitWidth, position.getY() + splitHeight);
    }

    public double getDistance(double x, double y, double x_min, double y_min, double x_max, double y_max) {
        if (x < x_min) {
            if (y < y_min) return HYPOT(x_min - x, y_min - y);
            if (y <= y_max) return x_min - x;
            return HYPOT(x_min - x, y_max - y);
        } else if (x <= x_max) {
            if (y < y_min) return y_min - y;
            if (y <= y_max) return 0;
            return y - y_max;
        } else {
            if (y < y_min) return HYPOT(x_max - x, y_min - y);
            if (y <= y_max) return x - x_max;
            return HYPOT(x_max - x, y_max - y);
        }
    }

    enum BoatSize {
        CARRIER(264, 124, 300),
        BATTLESHIP(261, 67, 250),
        DESTROYER(128, 32, 200),
        SUBMARINE(195, 48, 150),
        CRUISER(193, 48, 100);

        @Getter
        private float width, height;

        @Getter
        private int maxHealth;

        BoatSize(float width, float height, int maxHealth) {
            this.width = width;
            this.height = height;
            this.maxHealth = maxHealth;
        }

        public static BoatSize byName(String name) {
            for (BoatSize value : BoatSize.values()) {
                if (value.name().equalsIgnoreCase(name)) return value;
            }

            return null;
        }
    }
}
