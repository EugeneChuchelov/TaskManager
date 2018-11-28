package model;

import controller.taskFlag;

import java.util.Date;
import java.util.Observable;

public class Task extends Observable {
    private static final long DELAY_TIME = 300000;
    private String title;
    private String description;
    private Date date;
    private String contacts;
    private taskFlag flag = taskFlag.initial;

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

    public taskFlag getFlag() {
        return flag;
    }

    public void setFlag(taskFlag flag) {
        this.flag = flag;
    }

    public void delay() {
        date.setTime(date.getTime() + DELAY_TIME);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Название: ").append(title).append("\n");
        stringBuilder.append("Описание: ").append(description).append("\n");
        if (date != null) {
            stringBuilder.append("Дата и время: ").append(date.toString()).append("\n");
        } else {
            stringBuilder.append("Дата и время не указаны").append("\n");
        }
        stringBuilder.append("Контакты: ").append(contacts);
        return stringBuilder.toString();
    }
}
