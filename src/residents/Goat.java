package residents;

public class Goat extends Herbivore{
    {
        weight = 60;
        maxNumberPerLocation = 140;
        movementSpeed = 3;
        foodSaturationWeight = 10;
    }

    @Override
    public String toString() {
        return "\uD83D\uDC10";
    }
}
