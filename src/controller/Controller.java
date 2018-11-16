package controller;

import model.Journal;
import model.Task;
import view.View;

import java.io.*;
import java.util.List;

public class Controller {
    private static Journal journal = new Journal();
    private static boolean stop = false;
    private static String DEFAULT_SAVE_PATH = "tasks.bin";

    public static void main(String[] args) {
        load(DEFAULT_SAVE_PATH);
        while (!stop){
            parse(View.in());
        }
    }

    private static void parse(String string) {
        String command;
        if(string.indexOf(' ') != -1){
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
            case "выйти" :
                exit();
                break;
            default:
                View.unknownMessage();
                break;
        }
    }

    private static void add(String string) {
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

    private static void find(String string) {
        List<Task> found;
        found = journal.find(string);
        if (found.size() == 0) {
            View.notFoundMessage();
            return;
        }
        View.foundMessage(found);
    }

    private static void delete(String string) {
        List<Task> found = journal.find(string);
        if (found.size() == 1) {
            journal.delete(found.get(0));
            View.deletedMessage(found.get(0));
        } else {
            View.foundMessage(found);
            View.multiplyChoices();
            int index = Integer.parseInt(View.in());
            journal.delete(found.get(index));
            View.deletedMessage(found.get(index));
        }
    }

    private static void save(String path) {
        try {
            ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(path));
            outputStream.writeObject(journal);
            outputStream.close();
            View.savedMessage(path);
        } catch (IOException e) {
            View.errorMessage();
        }
    }

    private static void load(String path) {
        try {
            ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(path));
            journal = (Journal) inputStream.readObject();
            inputStream.close();
            View.loadedMessage(path);
        } catch (IOException | ClassNotFoundException e) {
            View.errorMessage();
        }
    }

    private static void exit(){
        save(DEFAULT_SAVE_PATH);
        stop = true;
    }
}
