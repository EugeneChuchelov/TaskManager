package controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import model.Journal;
import model.Task;
import view.View;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Controller {
    private static String DEFAULT_SAVE_PATH = "journal.json";
    private Journal journal = new Journal();
    private Alert alert;
    private boolean stop = false;

    public static void main(String[] args) {
        Controller controller = new Controller();
        controller.load(DEFAULT_SAVE_PATH);
        controller.alert = new Alert(controller);
        controller.alert.start();
        while (!controller.stop) {
            controller.parse(View.in(false));
        }
        controller.alert.interrupt();
    }

    public Journal getJournal() {
        return journal;
    }

    private void parse(String string) {
        String command;
        if (string.indexOf(' ') != -1) {
            command = string.substring(0, string.indexOf(' '));
        } else {
            command = string;
        }
        switch (command.toLowerCase()) {
            case "добавить":
                add(string.substring(string.indexOf(' ') + 1));
                break;
            case "найти":
                find(string.substring(string.indexOf(' ') + 1));
                break;
            case "удалить":
                delete(string.substring(string.indexOf(' ') + 1));
                break;
            case "сохранить":
                save(string.substring(string.indexOf(' ') + 1));
                break;
            case "загрузить":
                load(string.substring(string.indexOf(' ') + 1));
                break;
            case "показать":
                View.foundMessage(journal.getJournal());
                break;
            case "выйти":
                exit();
                break;
            case "завершить":
                alert.end();
                View.closed();
                break;
            case "отложить":
                alert.delay();
                View.delayed();
                break;
            default:
                View.unknownMessage();
                break;
        }
    }

    private void add(String string) {
        String[] args = string.split(",");
        for (int i = 0; i < args.length; i++) {
            args[i] = args[i].trim();
        }
        try {
            journal.add(args);
        } catch (Exception e) {
            View.errorMessage();
            return;
        }
        View.addedMessage();
    }

    private void find(String string) {
        List<Task> found;
        try {
            SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd hh:mm");
            Date date = ft.parse(string.trim());
            found = journal.find(date);
        } catch (Exception e) {
            found = journal.find(string.trim());
        }
        if (found.size() == 0) {
            View.notFoundMessage();
            return;
        }
        View.foundMessage(found);
    }

    private void delete(String string) {
        List<Task> found = journal.find(string);
        if (found.size() == 1) {
            journal.delete(found.get(0));
            View.deletedMessage(found.get(0));
        } else {
            View.foundMessage(found);
            int index = View.multiplyChoices(found.size());
            if (index < 0 || index > found.size()) {
                View.errorMessage();
            } else {
                journal.delete(found.get(index));
                View.deletedMessage(found.get(index));
            }
        }
    }

    //Используется для сохранения и загрузки библиотека gson
    private void save(String path) {
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        builder.setDateFormat("yyyy-MM-dd HH:mm");
        Gson gson = builder.create();
        try {
            FileWriter writer = new FileWriter(path);
            writer.write(gson.toJson(journal));
            writer.close();
            View.savedMessage(path);
        } catch (IOException e) {
            View.errorMessage();
        }
    }

    private void load(String path) {
        GsonBuilder builder = new GsonBuilder();
        builder.setDateFormat("yyyy-MM-dd HH:mm");
        Gson gson = builder.create();
        try {
            FileReader reader = new FileReader(path);
            journal = gson.fromJson(reader, Journal.class);
            reader.close();
            View.loadedMessage(path);
        } catch (IOException e) {
            View.errorMessage();
        }
    }

    private void exit() {
        save(DEFAULT_SAVE_PATH);
        stop = true;
    }

    void alertNow(Task task) {
        View.alertNow(task);
    }

    void alertSoon(Task task) {
        View.alertSoon(task);
    }
}
