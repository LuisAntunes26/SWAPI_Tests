package pt.ipbeja.twdm.pdm2.swapiapp;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BackgroundTasks {
    private static ExecutorService executor = Executors.newSingleThreadExecutor();

    public static void run(Runnable runnable){
        executor.execute(runnable);
    }
}
