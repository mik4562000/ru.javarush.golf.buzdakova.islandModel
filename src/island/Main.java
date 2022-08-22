package island;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    public static void main(String[] args) {
        IslandProperties.initialize();
        Island island = new Island(IslandProperties.getIslandHeight(), IslandProperties.getIslandWidth());
        island.print();

        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
        AtomicInteger i = new AtomicInteger(1);
        executorService.scheduleAtFixedRate(() -> {
            System.out.printf("It is a new day %d%n", i.getAndIncrement());
            island.liveAnotherDay();
            island.prepareLocationsForNewDay();
            island.print();

        }, 0, IslandProperties.getSchedulePeriod(), TimeUnit.SECONDS);
    }

}
