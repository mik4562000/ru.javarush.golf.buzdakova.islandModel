package residents;

public class Sheep extends Herbivore{
    {
        weight = 70;
        maxNumberPerLocation = 140;
        movementSpeed = 3;
        foodSaturationWeight = 15;
    }

    @Override
    public String toString() {
        return "\uD83D\uDC11";
    }
}
