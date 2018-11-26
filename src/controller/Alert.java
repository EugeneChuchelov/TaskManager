package controller;

import model.Journal;
import model.Task;

import java.util.Date;

public class Alert extends Thread {
    private static final long HALF_HOUR = 1800000;
    private Journal journal;

    public Alert(Journal journal) {
        this.journal = journal;
    }

    @Override
    public void run() {
        while (true){
            Date now = new Date();
            Date soon = new Date(now.getTime() + HALF_HOUR);
            for(Task task : journal.getJournal()){
                if(task.getDate().before(now)){
                    Controller.alertNow(task);
                } else if(task.getDate().before(soon)){
                    Controller.alertSoon(task);
                }
            }
            try {
                Thread.sleep(60000);
            } catch (InterruptedException e) {
                break;
            }
        }
    }
}
