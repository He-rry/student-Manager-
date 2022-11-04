package com.example.studentregapp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

public class ClassViewController implements Initializable {
    private final List<String> selectionItem = Arrays.asList("Price Low", "Price High");
    private List<Classroom> classroom = new ArrayList<>(DataStore.getInstance().classrooms);
    private final DataStore store = DataStore.getInstance();
    @FXML
    private TableColumn<String, Classroom> colClassName;
    @FXML
    private TableColumn<String, Classroom> colFees;
    @FXML
    private Button btnAdd;
    @FXML
    private Button btnBack;
    @FXML
    private Button btnRemove;
    @FXML
    private Button btnEdit;
    @FXML
    private ComboBox<String> cmbSelect;
    @FXML
    private TableView<Classroom> tableView;
    @FXML
    private TextField txtClassName;
    @FXML
    private TextField txtFess;
    private ArrayList<Classroom> names;
    private boolean isEditing = false;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btnAdd.setOnAction(e -> setBtnAdd(e));
        btnBack.setOnAction(e -> setBtnBack(e));
        btnEdit.setOnAction(e -> setupEdit());
        btnRemove.setOnAction(e -> {
            int index = tableView.getSelectionModel().getSelectedIndex();
            store.classrooms.remove(index);
            tableView.getItems().remove(index);
        });

        setupSelection();
        setupTableView();

        cmbSelect.setOnAction(event -> {
            if(cmbSelect.getSelectionModel().isSelected(0)){
                classroom = DataStore.getInstance().classrooms.stream()
                                                               .sorted(Comparator.comparing(Classroom::getFees))
                                                               .collect(Collectors.toList());
            }
            else
                classroom = DataStore.getInstance().classrooms;
            refreshTb();
        });
    }

    private void setupEdit() {
        Classroom selectedClass = tableView.getSelectionModel().getSelectedItem();
        txtClassName.setText(selectedClass.getClassroomName());
        txtFess.setText(selectedClass.getFees());
        isEditing = true;
        System.out.println("oo");
    }
    //   private Classroom instantiateClassroom() {}

    @FXML
    private void setBtnBack(ActionEvent event) {
        Button btnClicked = (Button) event.getSource();
        Stage stage = (Stage) btnClicked.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void setBtnAdd(ActionEvent event) {
        Classroom classroom = new Classroom();
        classroom.setClassroomName(txtClassName.getText());
        classroom.setFees(txtFess.getText());
        if (isEditing) {
            int index = tableView.getSelectionModel().getSelectedIndex();
            store.classrooms.set(index, classroom);
            tableView.getItems().set(index, classroom);
            isEditing = false;

        } else {
            store.classrooms.add(classroom);
            tableView.getItems().add(classroom);
        }
    }

    private void setupSelection() {
        cmbSelect.getItems().addAll(selectionItem);
        cmbSelect.getSelectionModel().select(0);

    }

    private void setupTableView() {
        tableView.getItems().addAll(store.classrooms);
    }

    private void restTable() {
        txtClassName.clear();
        txtFess.clear();
        tableView.refresh();
    }
    private void refreshTb(){
        tableView.getItems().clear();
        tableView.getItems().addAll(classroom);
    }

}
