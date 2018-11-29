package controller;

import model.Task;

import java.util.Date;

public class Alert extends Thread {
    private static final long HALF_HOUR = 1800000;
    private Controller controller;
    private Task currentTask;

    public Alert(Controller controller) {
        this.controller = controller;
    }

    @Override
    public void run() {
        while (true) {
            Date now = new Date();
            Date soonMin = new Date(now.getTime() + HALF_HOUR);
            Date soonMax = new Date(now.getTime() + HALF_HOUR - 30000);
            for (Task task : controller.getJournal().getJournal()) {
                if (task.getDate().before(now)) {
                    currentTask = task;
                    controller.alertNow(task);
                } else if (task.getDate().before(soonMin) &&
                        task.getDate().after(soonMax)) {
                    controller.alertSoon(task);
                }
            }
            try {
                Thread.sleep(30000);
            } catch (InterruptedException e) {
                break;
            }
        }
    }

    public void end() {
        controller.getJournal().delete(currentTask);
        currentTask = null;
    }

    public void delay() {
        controller.getJournal().delay(currentTask);
        currentTask = null;
    }
}
