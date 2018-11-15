package model;

import java.io.Serializable;
import java.util.Date;

public class Task implements Serializable {
    private String title;
    private String description;
    private Date date;
    private String contacts;

    public Task(String title, String description, Date date, String contacts) {
        this.title = title;
        this.description = description;
        this.date = date;
        this.contacts = contacts;
    }

    public String getTitle() {
        return title;
    }

    public Date getDate() {
        return date;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Название: ").append(title).append("\n");
        stringBuilder.append("Описание: ").append(description).append("\n");
        stringBuilder.append("Дата и время: ").append(date.toString()).append("\n");
        stringBuilder.append("Контакты: ").append(contacts);
        return stringBuilder.toString();
    }
}
