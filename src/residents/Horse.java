package residents;

public class Horse extends Herbivore{

    {
        weight = 400;
        maxNumberPerLocation = 20;
        movementSpeed = 4;
        foodSaturationWeight = 60;
    }

    @Override
    public String toString() {
        return "\uD83D\uDC0E";
    }
}
