package residents;

public class Sheep extends Herbivore{
    {
        WEIGHT = 70;
        MAX_NUMBER_PER_LOCATION = 140;
        MOVEMENT_SPEED = 3;
        FOOD_SATURATION_WEIGHT = 15;
    }

    @Override
    public String toString() {
        return "\uD83D\uDC11";
    }
}
