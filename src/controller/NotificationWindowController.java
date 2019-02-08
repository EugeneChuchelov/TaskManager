package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import model.Journal;
import model.Task;

public class NotificationWindowController {
    private static Journal journal;
    private static Task currentTask;
    private static Stage stage;
    @FXML
    Label titleLabel;
    @FXML
    Label descriptionLabel;
    @FXML
    Label timeLabel;
    @FXML
    Label contactsLabel;

    public static void setJournal(Journal journal) {
        NotificationWindowController.journal = journal;
    }

    public static void setCurrentTask(Task currentTask) {
        NotificationWindowController.currentTask = currentTask;
    }

    public static void setStage(Stage stage) {
        NotificationWindowController.stage = stage;
    }

    @FXML
    private void initialize() {
        if (currentTask != null) {
            titleLabel.setText(currentTask.getTitle());
            descriptionLabel.setText(currentTask.getDescription());
            timeLabel.setText(currentTask.getDate().toString());
            contactsLabel.setText(currentTask.getContacts());
        }
    }

    public void delay() {
        journal.delay(currentTask);
        stage.close();
    }

    public void close() {
        journal.remove(currentTask);
        stage.close();
    }
}
