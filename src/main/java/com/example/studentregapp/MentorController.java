package com.example.studentregapp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

public class MentorController implements Initializable {
    @FXML
    private Button BtnBack;

    @FXML
    private Button btnEdit;

    @FXML
    private Button btnRemove;

    @FXML
    private Button btnSave;

    @FXML
    private ComboBox<String> cmbChoice;
    @FXML
    private ComboBox<Classroom> comClass;

    @FXML
    private TableColumn<String, Classroom> colClassroom;

    @FXML
    private TableColumn<String, Mentor> colFees;

    @FXML
    private TableColumn<String, Mentor> colName;

    @FXML
    private TableView<Mentor> tbMentor;


    @FXML
    private TextField tfFees;

    @FXML
    private TextField tfName;


    private final List<String> choiceSelection = Arrays.asList("Language Teacher", "IT Teacher", "Art Teacher");
    private final DataStore store = DataStore.getInstance();
    private List<Mentor> mentors = new ArrayList<>(DataStore.getInstance().mentors);
    private final List<Classroom> classrooms = new ArrayList<>(DataStore.getInstance().classrooms);
    private boolean isEditing = false;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        btnSave.setOnAction(e -> onAddMentorClick());

        btnRemove.setOnAction(e -> onRemoveClick());

        btnEdit.setOnAction(event -> onEditClick(event));

        BtnBack.setOnAction(e -> onBackClick());

        //prepare data:
        setupTableView();
        setComBoxData();
        //class data
        setClassBox();
        cmbChoice.setOnAction(
                event -> {
                    if (cmbChoice.getSelectionModel().isSelected(0)) {
                        mentors = DataStore.getInstance().mentors.stream()
                                .filter(mentor -> {
                                    System.out.println(mentor.classroom.getClassroomName());
                                    return Objects.equals(mentor.classroom.getClassroomName(), "English") ||
                                            mentor.classroom.getClassroomName().equals("Korean") ||
                                            mentor.classroom.getClassroomName().equals("German");
                                }).collect(Collectors.toList());
                        System.out.println(mentors.stream().count());
                    }else if(cmbChoice.getSelectionModel().isSelected(1)){
                        mentors = DataStore.getInstance().mentors.stream()
                                                                  .filter(mentor -> {
                                                                   return Objects.equals(mentor.classroom.getClassroomName(),
                                                                           "Business IT");
                                                                  }).collect(Collectors.toList());
                        System.out.println(mentors.stream().count());
                    }else if (cmbChoice.getSelectionModel().isSelected(2)){
                    mentors =DataStore.getInstance().mentors.stream()
                            .filter(mentor -> {
                            return Objects.equals(mentor.classroom.getClassroomName(),"Guitar") ||
                                    mentor.classroom.getClassroomName().equals("Digital painting");
                            }).collect(Collectors.toList());
                    }
                    else {
                        mentors = DataStore.getInstance().mentors;
                    }
                    resetTB();
                });


    }

    private void onBackClick() {
        Stage mentorStage = (Stage) BtnBack.getScene().getWindow();
        mentorStage.close();
    }

    private void onEditClick(ActionEvent event) {
        Mentor selectedMentor = tbMentor.getSelectionModel().getSelectedItem();
        tfName.setText(selectedMentor.getName());
        tfFees.setText(selectedMentor.getFees());
        comClass.getSelectionModel().select(selectedMentor.getClassroom());
        isEditing = true;
    }

    private void onRemoveClick() {
        int index = tbMentor.getSelectionModel().getSelectedIndex();
        store.mentors.remove(index);
        tbMentor.getItems().remove(index);
    }

    private void onAddMentorClick() {
        Mentor mentor = new Mentor();
        mentor.setName(tfName.getText());
        mentor.setFees(tfFees.getText());
        mentor.setClassroom(comClass.getSelectionModel().getSelectedItem());
        if (isEditing) {
            int index = tbMentor.getSelectionModel().getSelectedIndex();
            store.mentors.set(index, mentor);
            tbMentor.getItems().set(index, mentor);
            isEditing = false;
        } else
            store.mentors.add(mentor);
        tbMentor.getItems().add(mentor);
    }

    private void setupTableView() {
        tbMentor.getItems().addAll(store.mentors);
    }

    //combo box class
    private void setClassBox() {
        comClass.getItems().addAll(store.classrooms);
        comClass.getSelectionModel().select(null);
    }

    private void resetTable() {
        tfName.clear();
        tfFees.clear();
        comClass.getSelectionModel().select(null);
    }

    //sort combo box selection data
    private void setComBoxData() {
        cmbChoice.getItems().addAll(choiceSelection);
    }

    private void resetTB() {
        tbMentor.getItems().clear();
        tbMentor.getItems().addAll(mentors);
    }
}
