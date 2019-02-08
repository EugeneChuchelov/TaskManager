package controller;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Journal;
import model.Task;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainWindowController {
    @FXML
    TextField findField;
    @FXML
    private TableView<Task> table;
    @FXML
    private TableColumn<Task, Date> dateColumn;
    @FXML
    private TableColumn<Task, String> titleColumn;
    @FXML
    private TableColumn<Task, String> descriptionColumn;
    @FXML
    private TableColumn<Task, String> contactsColumn;
    private Journal journal;

    @FXML
    private void initialize() {
        journal = new Journal();
        journal.load();
        AddWindowController.setJournal(journal);
        NotificationWindowController.setJournal(journal);

        Alert alert = new Alert(journal, this);
        alert.setDaemon(true);
        alert.start();

        dateColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getDate()));
        titleColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTitle()));
        descriptionColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDescription()));
        contactsColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getContacts()));
        table.setItems(journal.getJournal());
    }

    public void add() {
        Stage window = new Stage();
        window.setTitle("Добавление новой задачи");
        try {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("view/addWindow.fxml"));
            window.setScene(new Scene(root, 600, 400));
        } catch (IOException e) {
            e.printStackTrace();
        }
        AddWindowController.setStage(window);
        window.show();
    }

    public void find() {
        ObservableList<Task> foundTasks;
        if (!findField.getText().equals("")) {
            try {
                SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd hh:mm");
                Date date = ft.parse(findField.getText());
                foundTasks = journal.find(date);
            } catch (Exception e) {
                foundTasks = journal.find(findField.getText());
            }
            table.setItems(foundTasks);
        } else {
            table.setItems(journal.getJournal());
        }
    }

    public void remove() {
        int row = table.getSelectionModel().getSelectedIndex();
        table.getItems().remove(row);
    }

    public void save() {
        journal.save();
    }

    public void load() {
        journal.load();
    }

    public void alert(Task currentTask) {
        NotificationWindowController.setCurrentTask(currentTask);
        Stage window = new Stage();
        window.setTitle("Пришло время задачи");
        try {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("view/notificationWindow.fxml"));
            window.setScene(new Scene(root, 600, 240));
        } catch (IOException e) {
            e.printStackTrace();
        }
        NotificationWindowController.setStage(window);
        window.show();
    }

    public void alertSoon(Task currentTask) {
        HalfHourWindowController.setCurrentTask(currentTask);
        Stage window = new Stage();
        window.setTitle("Осталось 30 минут");
        try {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("view/halfHourWindow.fxml"));
            window.setScene(new Scene(root, 600, 240));
        } catch (IOException e) {
            e.printStackTrace();
        }
        HalfHourWindowController.setStage(window);
        window.show();
    }
}
