package model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;


public class Journal {
    private List<Task> journal;


    public Journal() {
        this.journal = new LinkedList<>();
    }

    public List<Task> getJournal() {
        return journal;
    }

    public void add(String[] args) throws ParseException {
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date date = ft.parse(args[2]);
        Task task = new Task(args[0], args[1], date, args[3]);
        journal.add(task);
    }

    public List<Task> find(String title) {
        List<Task> found = new LinkedList<>();
        try {
            Pattern pattern = Pattern.compile(title);
            for (Task task : journal) {
                if (pattern.matcher(task.getTitle()).matches()) {
                    found.add(task);
                }
            }
            return found;
        } catch (Exception e) {
            return found;
        }
    }

    public List<Task> find(Date date) {
        List<Task> found = new LinkedList<>();
        for (Task task : journal) {
            if (task.getDate().equals(date)) {
                found.add(task);
            }
        }
        return found;
    }

    public void delete(Task desired) {
        journal.remove(desired);
    }

    public void delay(Task desired) {
        journal.get(journal.indexOf(desired)).delay();
    }
}
