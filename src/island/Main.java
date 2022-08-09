package island;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        IslandProperties.initialize();
        Island island = new Island(IslandProperties.getIslandHeight(), IslandProperties.getIslandWidth());
        island.print();
        /*ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
        executorService.scheduleAtFixedRate(() -> {
            island.liveAnotherDay();
            island.print();
        }, 0, 30, TimeUnit.SECONDS);*/
        System.out.println("It is a new day");
        island.liveAnotherDay();
        island.print();
        /*
        try {
            Thread.sleep(120*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        }, 0, IslandProperties.getSchedulePeriod(), TimeUnit.SECONDS);
    }

}
