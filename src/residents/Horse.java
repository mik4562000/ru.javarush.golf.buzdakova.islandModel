package residents;

public class Horse extends Herbivore{

    {
        WEIGHT = 400;
        MAX_NUMBER_PER_LOCATION = 20;
        MOVEMENT_SPEED = 4;
        FOOD_SATURATION_WEIGHT = 60;
    }

    @Override
    public String toString() {
        return "\uD83D\uDC0E";
    }
}
