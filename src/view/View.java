package view;

import model.Task;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class View {
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void errorMessage() {
        System.out.println("Произла ошибка");
    }

    public static void addedMessage() {
        System.out.println("Добавлена запись");
    }

    public static void foundMessage(List<Task> tasks) {
        System.out.println("Найдены записи");
        System.out.println();
        for (Task task : tasks) {
            System.out.println(task.toString());
            System.out.println();
        }
    }

    public static void notFoundMessage() {
        System.out.println("Записей не найдено");
    }

    public static void deletedMessage(Task task) {
        System.out.println("Запись была удалена");
        System.out.println(task.toString());
    }

    public static int multiplyChoices(int count) {
        System.out.println("Какую запись удалить? (0-" + count + ")");
        return Integer.parseInt(in(false));
    }

    public static void savedMessage(String path) {
        System.out.println("Журнал сохранён на диск в файл " + path);
    }

    public static void loadedMessage(String path) {
        System.out.println("Журнал загружен с диска из файла " + path);
    }

    public static void unknownMessage() {
        System.out.println("Неизвестная команда");
    }

    public static void alertNow(Task task) {
        System.out.println("Внимание! Пришло время задачи:");
        System.out.println(task.toString());
        System.out.println("Завершить или отложить?");
    }

    public static void alertSoon(Task task) {
        System.out.println("Внимание! Через полчаса придёт время задачи:");
        System.out.println(task.toString());
    }

    public static void closed() {
        System.out.println("Задача была завершена");
    }

    public static void delayed() {
        System.out.println("Задача была отложена на 5 минут");
    }

    public static String in(Boolean createNew) {
        try {
            return reader.readLine();
        } catch (IOException e) {
            System.err.println("Произошла ошибка при вводе!");
            return "";
        }
    }
}
