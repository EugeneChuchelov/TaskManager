package model;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Journal implements Serializable {
    private List<Task> journal;

    public Journal() {
        this.journal = new LinkedList<>();
    }

    public List<Task> getJournal() {
        return journal;
    }

    public void add(String[] args){
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        Date date = null;
        try {
            date = ft.parse(args[2]);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        journal.add(new Task(args[0], args[1], date, args[3]));
    }

    public List<Task> find(String string){
        try{
            SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd hh:mm");
            Date date = ft.parse(string.trim());
            return search(date);
        } catch (Exception e){
            return search(string.trim());
        }

    }

    private List<Task> search(String title){
        Pattern pattern = Pattern.compile(title);
        List<Task> found = new LinkedList<>();
        for(Task task : journal){
            if(pattern.matcher(task.getTitle()).matches()){
                found.add(task);
            }
        }
        return found;
    }

    public List<Task> search(Date date){
        List<Task> found = new LinkedList<>();
        for(Task task : journal){
            if(task.getDate().equals(date)){
                found.add(task);
            }
        }
        return found;
    }

    public void delete(Task desired){
        journal.remove(desired);
    }
}
