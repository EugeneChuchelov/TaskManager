package model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.regex.Pattern;


public class Journal {
    private static String SAVE_PATH = "journal.json";
    private ObservableList<Task> journal;

    public ObservableList<Task> getJournal() {
        return journal;
    }

    public void add(Task task) {
        journal.add(task);
    }

    public ObservableList<Task> find(String title) {
        ObservableList<Task> found = FXCollections.observableArrayList();
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

    public ObservableList<Task> find(Date date) {
        ObservableList<Task> found = FXCollections.observableArrayList();
        for (Task task : journal) {
            if (task.getDate().equals(date)) {
                found.add(task);
            }
        }
        return found;
    }

    public void remove(Task desired) {
        journal.remove(desired);
    }

    public void delay(Task desired) {
        journal.get(journal.indexOf(desired)).delay();
    }

    //

    public void save() {
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        builder.setDateFormat("yyyy-MM-dd HH:mm");
        Gson gson = builder.create();
        try {
            FileWriter writer = new FileWriter(SAVE_PATH);
            writer.write(gson.toJson(journal));
            writer.close();
        } catch (IOException e) {

        }
    }

    public void load() {
        GsonBuilder builder = new GsonBuilder();
        builder.setDateFormat("yyyy-MM-dd HH:mm");
        builder.registerTypeAdapter(ObservableList.class, new ObservableListDeserializer());
        Gson gson = builder.create();
        try {
            FileReader reader = new FileReader(SAVE_PATH);
            journal = gson.fromJson(reader, ObservableList.class);
            reader.close();
        } catch (IOException e) {

        }
    }
}
