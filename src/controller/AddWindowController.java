package controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Journal;
import model.Task;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AddWindowController {
    static Journal journal;
    static Stage stage;
    @FXML
    TextField titleField;
    @FXML
    TextField descriptionField;
    @FXML
    DatePicker datePicker;
    @FXML
    Spinner<Integer> hours;
    @FXML
    Spinner<Integer> minutes;
    @FXML
    TextField contactsField;
    @FXML
    Button acceptButton;
    @FXML
    AnchorPane window;

    public static void setJournal(Journal journal) {
        AddWindowController.journal = journal;
    }

    public static void setStage(Stage stage) {
        AddWindowController.stage = stage;
    }

    @FXML
    public void initialize() {
        SpinnerValueFactory<Integer> hoursFactory =
                new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 23, 0);
        SpinnerValueFactory<Integer> minutesFactory =
                new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59, 0);
        hours.setValueFactory(hoursFactory);
        minutes.setValueFactory(minutesFactory);
    }

    public void accept() {
        String title = titleField.getText();
        String description = descriptionField.getText();
        String time = datePicker.getValue().toString() + " " + hours.getValue() + ":" + minutes.getValue();//timeField.getText();
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date date = new Date();
        try {
            date = ft.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String contacts = contactsField.getText();
        Task task = new Task(title, description, date, contacts);
        journal.add(task);
        stage.close();
    }

    public void decline() {
        stage.close();
    }
}
