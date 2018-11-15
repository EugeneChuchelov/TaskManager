package view;

import controller.Controller;
import model.Task;

import java.util.List;
import java.util.Scanner;

public class View {
    private static Scanner scanner = new Scanner(System.in);

    public static void errorMessage(){
        System.out.println("Произла ошибка");
    }

    public static void addedMessage(){
        System.out.println("Добавлена запись");
    }

    public static void foundMessage(List<Task> tasks){
        System.out.println("Найдены записи");
        System.out.println();
        for(Task task : tasks){
            System.out.println(task.toString());
            System.out.println();
        }
    }

    public static void notFoundMessage(){
        System.out.println("Записей не найдено");
    }

    public static void deletedMessage(Task task){
        System.out.println("Запись была удалена");
        System.out.println(task.toString());
    }

    public static void multiplyChoices(){
        System.out.println("Какую запись удалить?");
    }

    public static void savedMessage(String path){
        System.out.println("Журнал сохранён на диск в файл " + path);
    }

    public static void loadedMessage(String path){
        System.out.println("Журнал загружен с диска из файла " + path);
    }

    public static void unknownMessage(){
        System.out.println("Неизвестная команда");
    }

    public static String in(){
        return scanner.nextLine();
    }

    public static void main(String[] args) {
        while (true){
            try{
                Controller.parse(in());
            } catch (StringIndexOutOfBoundsException e){
                System.out.println("У команды должны быть аргументы");
            }
        }
    }
}
