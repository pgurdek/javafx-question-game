package pl.UtilsTime;

import javafx.concurrent.Task;

public class Tasks {

    public static Task<Void> sleep(final Integer milliseconds) {
        final Task<Void> sleeper = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                try {
                    Thread.sleep(milliseconds);
                } catch (InterruptedException e) {
                }
                return null;
            }
        };
        new Thread(sleeper).start();
        return sleeper;
    }
}