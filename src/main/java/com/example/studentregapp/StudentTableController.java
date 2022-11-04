package com.example.studentregapp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;


public class StudentTableController implements Initializable {
    @FXML
    private Button btnEdit;

    @FXML
    private Button btnEli;

    @FXML
    private Button btnAdd;

    @FXML
    private ComboBox<String> cmSelection;

    @FXML
    private TableColumn<String, Student> colAddress;

    @FXML
    private TableColumn<String, Student> colAge;
    @FXML
    private TableColumn<String, Student> colGender;

    @FXML
    private TableColumn<String, Classroom> colClass;

    @FXML
    private TableColumn<String, Mentor> colClmentor;

    @FXML
    private TableColumn<LocalDate, Student> colDob;

    @FXML
    private TableColumn<String, Student> colEmail;

    @FXML
    private TableColumn<String, Student> colNRc;

    @FXML
    private TableColumn<String, Student> colName;

    @FXML
    private Label lblTxt;

    @FXML
    private TableView<Student> taBlView;

    @FXML
    private TextField txtSearch;
    @FXML
    private Button btnBack;

    @FXML
    private ComboBox<Classroom> comClass;

    @FXML
    private ComboBox<String> comGender;

    @FXML
    private ComboBox<Mentor> comMentor;

    @FXML
    private DatePicker doBirth;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtAge;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtNrc;
    @FXML
    private ImageView img;
    private final List<String> comSelection = Arrays.asList("Male", "Female");
    private final List<String> genderSelectItems = Arrays.asList("Male", "Female", "Rather not to say");
    private final DataStore store = DataStore.getInstance();
    private List<Student> students = new ArrayList<>(DataStore.getInstance().students);
    private List<Classroom> classrooms = new ArrayList<>(DataStore.getInstance().classrooms);
    private List<Mentor> mentors = new ArrayList<>(DataStore.getInstance().mentors);
    private boolean isEditing = false;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btnEli.setOnAction(e -> setBtnEli());
        btnEdit.setOnAction(e -> setEdit());
        btnBack.setOnAction(e -> setBtnBack());
        setupClassroom();
        cmSelection.setOnAction(e -> {
            if (cmSelection.getSelectionModel().isSelected(0)) {
                students = DataStore.getInstance().students.stream()
                        .filter(student -> {
                            return student.getGender().equals("Male");

                        }).collect(Collectors.toList());
            } else if (cmSelection.getSelectionModel().isSelected(1)) {
                students = DataStore.getInstance().students.stream()
                        .filter(student -> {
                            return student.getGender().equals("Female");
                        }).collect(Collectors.toList());
            } else {
                students = DataStore.getInstance().students;
            }
            resetTable();

        });
        comMentor.setOnAction(e -> {
            if (comMentor.getSelectionModel().isSelected(0)) {
                classrooms = DataStore.getInstance().classrooms.stream().filter(classroom -> {

                    return classroom.getClassroomName().equals("English");
                }).collect(Collectors.toList());
            } else {
                classrooms = DataStore.getInstance().classrooms;
            }
            comClass.getItems().clear();
            comClass.getItems().addAll(classrooms);
        });

        setupGender();
        setupMentor();
        setupSortList();
        setTxtSearch();


        btnAdd.setOnAction(event -> addStudent());


        taBlView.getItems().addAll(students);

    }


    public void reFreshTable() {
        txtName.clear();
        txtAddress.clear();
        txtNrc.clear();
        txtEmail.clear();
        txtAge.clear();
        comClass.getSelectionModel().clearSelection();
        comMentor.getSelectionModel().clearSelection();
        doBirth.setValue(null);
        comGender.getSelectionModel().clearSelection();
    }

    private void setBtnEli() {
        int index = taBlView.getSelectionModel().getSelectedIndex();
        taBlView.getItems().remove(index);
    }

    private void setEdit() {
        Student selectedStudent = taBlView.getSelectionModel().getSelectedItem();
        txtName.setText(selectedStudent.getName());
        txtAge.setText(selectedStudent.getAge());
        txtNrc.setText(selectedStudent.getNRCNumber());
        txtEmail.setText(selectedStudent.getEmail());
        txtAddress.setText(selectedStudent.getAddress());
        comClass.getSelectionModel().select(selectedStudent.getClassroom());
        comGender.getSelectionModel().select(selectedStudent.getGender());
        comMentor.getSelectionModel().select(selectedStudent.getMentor());
        doBirth.setValue(selectedStudent.getDateOfBirth());
        isEditing = true;

    }

    private void setBtnBack() {
        Stage currentStage = (Stage) btnBack.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HomeController.class.getResource("Home.fxml"));
        try {
            Scene backHome = new Scene(fxmlLoader.load());
            currentStage.setScene(backHome);
            currentStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    // cmBox Data:

    private void setupGender() {
        comGender.getItems().addAll(genderSelectItems);
        comGender.getSelectionModel().select(2);
    }

    //comBox classroom

    private void setupClassroom() {
        comClass.getItems().addAll(store.classrooms);
        comClass.getSelectionModel().select(0);
    }

    //comBox Mentor


    private void setupMentor() {
        comMentor.getItems().addAll(store.mentors);
        comMentor.getSelectionModel().select(0);
    }

    //comBox select
    private void setupSortList() {
        cmSelection.getItems().addAll(comSelection);
        cmSelection.getSelectionModel().select(null);
    }

    private void addStudent() {
        Student student = new Student();
        student.setName(txtName.getText());
        student.setAge(txtAge.getText());
        student.setGender(comGender.getSelectionModel().getSelectedItem());
        student.setEmail(txtEmail.getText());
        student.setAddress(txtAddress.getText());
        student.setClassroom(comClass.getSelectionModel().getSelectedItem());
        student.setDateBirth(doBirth.getValue());
        student.setMentor(comMentor.getSelectionModel().getSelectedItem());
        student.setNRCumber(txtNrc.getText());
        if (isEditing) {
            int index = taBlView.getSelectionModel().getSelectedIndex();
            store.students.set(index, student);
            taBlView.getItems().set(index, student);
            isEditing = false;
        } else
            store.students.add(student);
        taBlView.getItems().add(student);
        reFreshTable();

    }

    //search img
    private void onClicked(ActionEvent e) {
        taBlView.getItems().clear();
        taBlView.getItems().addAll();
    }

    private void setTxtSearch() {

        txtSearch.textProperty().addListener(((observableValue, oldValue, newValue) -> {
            if (!newValue.isEmpty()) {
                //Filter part
                students = DataStore.getInstance().students.stream()
                        .filter(student -> student.getName().toLowerCase().startsWith(newValue.toLowerCase()))
                        .collect(Collectors.toList());

            } else {
                students = DataStore.getInstance().students;
            }
            resetTable();

        }));
    }

    private void resetTable() {
        taBlView.getItems().clear();
        taBlView.getItems().addAll(students);
    }

    private void resetComBox() {
        comClass.getItems().clear();
        comClass.getItems().addAll(classrooms);
    }
}
