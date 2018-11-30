package model;

import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Task {
    private static final long DELAY_TIME = 300000;
    private String title;
    private String description;
    private Date date;
    private String contacts;

    Task(String title, String description, Date date, String contacts) {
        this.title = title;
        this.description = description;
        this.date = date;
        this.contacts = contacts;
    }

    String getTitle() {
        return title;
    }

    public Date getDate() {
        return date;
    }

    void delay() {
        date.setTime(date.getTime() + DELAY_TIME);
    }

    @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("E d MMMM yyyy, HH:mm", formatSymbols);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Название: ").append(title).append("\n");
        stringBuilder.append("Описание: ").append(description).append("\n");
        if (date != null) {
            stringBuilder.append("Дата и время: ").append(dateFormat.format(date)).append("\n");
        } else {
            stringBuilder.append("Дата и время не указаны").append("\n");
        }
        stringBuilder.append("Контакты: ").append(contacts);
        return stringBuilder.toString();
    }

    private static DateFormatSymbols formatSymbols = new DateFormatSymbols() {
        @Override
        public String[] getShortWeekdays() {
            return new String[]{"", "Пн.", "Вт.", "Ср.", "Чт.", "Пт.", "Сб.", "Вс."};
        }

        @Override
        public String[] getMonths() {
            return new String[]{"января", "февраля", "марта", "апреля", "мая", "июня",
                    "июля", "августа", "сентября", "октября", "ноября", "декабря"};
        }

    };
}
