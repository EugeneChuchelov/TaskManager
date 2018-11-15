package controller;

import model.Journal;
import model.Task;
import view.View;

import java.io.IOException;
import java.util.List;

public class Controller {
    String addExample    = "добавить встреча, пойти и найти, 2007-11-24 16:30, 992-11-44";
    String findExample0  = "найти встреча";
    String findExample1  = "найти 2007-11-24 16:30";
    private static Journal journal = new Journal();

    public static void parse(String string) throws StringIndexOutOfBoundsException{
        String command = string.substring(0, string.indexOf(' '));
        switch (command.toLowerCase()){
            case "добавить" :
                add(string.substring(string.indexOf(' ')));
                break;
            case "найти" :
                find(string.substring(string.indexOf(' ')));
                break;
            case "удалить" :
                delete(string.substring(string.indexOf(' ')));
                break;
            case "сохранить" :
                save(string.substring(string.indexOf(' ')));
                break;
            case "загрузить" :
                load(string.substring(string.indexOf(' ')));
                break;
            case "показать" :
                View.foundMessage(journal.getJournal());
                break;
            default:
                View.unknownMessage();
                break;
        }
    }

    private static void add(String string){
        String[] args = string.split(",");
        for(int i = 0; i < args.length; i++){
            args[i] = args[i].trim();
        }
        try {
            journal.add(args);
        } catch (Exception e){
            View.errorMessage();
            return;
        }
        View.addedMessage();
    }

    private static void find(String string){
        List<Task> found;
        found = journal.find(string);
        if(found.size() == 0){
            View.notFoundMessage();
            return;
        }
        View.foundMessage(found);
    }

    private static void delete(String string){
        List<Task> found = journal.find(string);
        if(found.size() == 1){
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

    private static void save(String path){
        try {
            Save.save(journal, path);
            View.savedMessage(path);
        } catch (IOException e) {
            View.errorMessage();
        }
    }

    private static void load(String path){
        try {
            journal = Save.load(path);
            View.loadedMessage(path);
        } catch (IOException | ClassNotFoundException e) {
            View.errorMessage();
        }
    }
}
