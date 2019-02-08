package controller;

import javafx.application.Platform;
import model.Journal;
import model.Task;

import java.util.Date;

public class Alert extends Thread {
    private static final long HALF_HOUR = 1800000;
    private Journal journal;
    private MainWindowController controller;

    Alert(Journal journal, MainWindowController controller) {
        this.journal = journal;
        this.controller = controller;
    }

    @Override
    public void run() {
        while (true) {
            Date now = new Date();
            Date soonMin = new Date(now.getTime() + HALF_HOUR);
            Date soonMax = new Date(now.getTime() + HALF_HOUR - 30000);
            for (Task task : journal.getJournal()) {
                if (task.getDate().before(now)) {
                    Platform.runLater(() -> controller.alert(task));
                } else if (task.getDate().before(soonMin) &&
                        task.getDate().after(soonMax)) {
                    Platform.runLater(() -> controller.alertSoon(task));
                }
            }
            try {
                Thread.sleep(30000);
            } catch (InterruptedException e) {
                break;
            }
        }
    }
}
