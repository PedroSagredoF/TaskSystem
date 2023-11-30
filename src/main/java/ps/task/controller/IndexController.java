package ps.task.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.cell.PropertyValueFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ps.task.model.Tasks;
import ps.task.sevice.TaskService;

import javafx.scene.control.*;
import java.net.URL;
import java.util.ResourceBundle;

@Component
public class IndexController implements Initializable {

    private static final Logger logger =
            LoggerFactory.getLogger(IndexController.class);

    @Autowired
    private TaskService taskService;

    @FXML
    private TableView<Tasks> tableView;

    @FXML
    private TableColumn<Tasks, Integer> idColumn;

    @FXML
    private TableColumn<Tasks, String> taskColumn;

    @FXML
    private TableColumn<Tasks, String> ownerColumn;

    @FXML
    private TableColumn<Tasks, String> statusColumn;

    @FXML
    private TextField taskField;

    @FXML
    private TextField ownerField;

    @FXML
    private TextField statusField;

    private Integer idTaskIntern;

    private final ObservableList<Tasks> tasksList =
            FXCollections.observableArrayList();



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Select only 1 register
        tableView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        columnLoad();
       showTasksList();
   }

    private void showTasksList() {
        logger.info("Show Task Executed");
        tasksList.clear();
        tasksList.addAll(taskService.taskList());
        tableView.setItems(tasksList);
    }

    private void columnLoad() {
        logger.info("Column Load Executed");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("task_Id"));
        taskColumn.setCellValueFactory(new PropertyValueFactory<>("taskName"));
        ownerColumn.setCellValueFactory(new PropertyValueFactory<>("owner"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
    }

    @FXML
    private void addTask (){
        if (taskField.getText().isEmpty()){
            showMessage("Validation Error", "Must insert task");
            taskField.requestFocus();
            return;
        } else {
            var task = new Tasks();
            receiveFormData(task);
            task.setTask_Id(null);
            taskService.saveTask(task);
            showMessage("Info", "Task Added");
            cleanForm();
            showTasksList();
        }
    }

    public void showTaskForm(){
        var task = tableView.getSelectionModel().getSelectedItem();
        if(task != null){
            idTaskIntern = task.getTask_Id();
            taskField.setText(task.getTaskName());
            ownerField.setText(task.getOwner());
            statusField.setText(task.getStatus());
        }

    }

    public void editTask(){
        if(idTaskIntern == null){
            showMessage("Info", "Must select Task");
            return;
        }
        if(taskField.getText().isEmpty()){
            showMessage("Validation Error:", "Must select Task");
            taskField.requestFocus();
            return;
        }

        var task = new Tasks();
        receiveFormData(task);
        taskService.saveTask(task);
        showMessage("Info", "Task Changed");
        cleanForm();
        showTasksList();
    }

    public void cleanForm() {
        idTaskIntern = null;
        taskField.clear();
        ownerField.clear();
        statusField.clear();
    }

    private void showMessage(String title, String message){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void receiveFormData(Tasks task){
        if(idTaskIntern !=null){
            task.setTask_Id(idTaskIntern);
        }
        task.setTaskName(taskField.getText());
        task.setOwner(ownerField.getText());
        task.setStatus(statusField.getText());
    }

    public void deleteTask() {
        if(idTaskIntern == null){
            showMessage("Info", "Must select Task");
            return;
        }
        if(taskField.getText().isEmpty()){
            showMessage("Validation Error:", "Must select Task");
            taskField.requestFocus();
            return;
        }

        var task = new Tasks();
        receiveFormData(task);
        taskService.deleteTask(task);
        showMessage("Info", "Task Deleted");
        cleanForm();
        showTasksList();
    }
}
