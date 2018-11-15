package controller;

import model.Journal;

import java.io.*;

public class Save {
    static public void save(Journal journal, String path) throws IOException {
        ObjectOutputStream outputStream=new ObjectOutputStream(new FileOutputStream(path));
        outputStream.writeObject(journal);
        outputStream.close();
    }

    static public Journal load(String path) throws IOException, ClassNotFoundException {
        ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(path));
        Journal journal = (Journal) inputStream.readObject();
        inputStream.close();
        return journal;
    }
}
