package island;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class IslandProperties {

    private static int islandHeight;
    private static int islandWidth;
    private static int schedulePeriod;

    public static void initialize() {

        Properties property = new Properties();

        try (FileInputStream fis = new FileInputStream("./src/resources/config.properties")) {

            property.load(fis);

            islandHeight = Integer.parseInt(property.getProperty("island.height"));
            islandWidth = Integer.parseInt(property.getProperty("island.width"));
            schedulePeriod = Integer.parseInt(property.getProperty("schedule.cycle"));

        } catch (IOException e) {
            System.err.println("ERROR: Properties' File is missing!");
        }
    }

    public static int getIslandHeight() {
        return islandHeight;
    }

    public static int getIslandWidth() {
        return islandWidth;
    }

    public static int getSchedulePeriod() {
        return schedulePeriod;
    }
}
