package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import model.Task;

public class HalfHourWindowController {
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

    public static void setCurrentTask(Task currentTask) {
        HalfHourWindowController.currentTask = currentTask;
    }

    public static void setStage(Stage stage) {
        HalfHourWindowController.stage = stage;
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

    public void close() {
        stage.close();
    }
}
