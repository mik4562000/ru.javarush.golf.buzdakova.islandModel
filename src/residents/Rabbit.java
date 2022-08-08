package residents;

public class Rabbit extends Herbivore{
    {
        WEIGHT = 2;
        MAX_NUMBER_PER_LOCATION = 150;
        MOVEMENT_SPEED = 2;
        FOOD_SATURATION_WEIGHT = 0.45;
    }

    @Override
    public String toString() {
        return "\uD83D\uDC07";
    }
}
