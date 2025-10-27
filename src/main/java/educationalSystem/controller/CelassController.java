package educationalSystem.controller;

import educationalSystem.model.service.CelassService;
import educationalSystem.model.service.LessonService;
import educationalSystem.model.service.SessionService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lombok.extern.log4j.Log4j2;
import educationalSystem.model.entity.Celass;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

@Log4j2
public class CelassController implements Initializable {

    @FXML
    private TextField classCodeText, sessionCodeText, lessonCodeText, searchClassCode;

    @FXML
    private Button saveButton, editButton, deleteButton;

    @FXML
    private TableView<Celass> classTable;

    @FXML
    private TableColumn<Celass, Integer> classCodeColumn, sessionColumn, lessonColumn, teacherColumn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            resetForm();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Error Loading Data !!!", ButtonType.OK);
            alert.show();
        }

        saveButton.setOnAction(event -> {
            try{
                Celass celass =
                        Celass
                                .builder()
                                .classCode(Integer.parseInt(classCodeText.getText()))
                                .session(SessionService.getService().findById(Integer.parseInt(sessionCodeText.getText())))
                                .lesson(LessonService.getService().findById(Integer.parseInt(lessonCodeText.getText())))
                                .build();
                CelassService.getService().save(celass);
                log.info("Successfully Saved Class");
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Successfully Saved Class", ButtonType.OK);
                alert.show();
                resetForm();
            } catch (Exception e) {
                log.error("Error Saving Data", e.getMessage());
                Alert alert = new Alert(Alert.AlertType.ERROR, "Error Saving Data !!!" + e.getMessage(), ButtonType.OK);
                alert.show();
            }
        });

        editButton.setOnAction(event -> {
            try{
                Celass celass =
                        Celass
                                .builder()
                                .classCode(Integer.parseInt(classCodeText.getText()))
                                .session(SessionService.getService().findById(Integer.parseInt(sessionCodeText.getText())))
                                .lesson(LessonService.getService().findById(Integer.parseInt(lessonCodeText.getText())))
                                .build();
                CelassService.getService().edit(celass);
                log.info("Successfully Edited Class");
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Successfully Edited Class", ButtonType.OK);
                alert.show();
                resetForm();
            } catch (Exception e) {
                log.error("Error Editing Data", e.getMessage());
                Alert alert = new Alert(Alert.AlertType.ERROR, "Error Editing Data !!!" + e.getMessage(), ButtonType.OK);
                alert.show();
            }
        });

        deleteButton.setOnAction(event -> {
            try{
                CelassService.getService().delete(Integer.parseInt(classCodeText.getText()));
                log.info("Successfully Deleted Class");
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Deleted Successfully\n" + classCodeText.getText(), ButtonType.OK);
                alert.show();
                resetForm();
            } catch (Exception e) {
                log.error("Error Deleting Data", e.getMessage());
                Alert alert = new Alert(Alert.AlertType.ERROR, "Error Deleting Data !!! " + e.getMessage(), ButtonType.OK);
                alert.show();
            }
        });

        searchClassCode.setOnAction(event -> searchByClassCode());

        classTable.setOnMouseReleased(event -> selectFromTable());

        classTable.setOnKeyReleased(event -> selectFromTable());
    }

    private void resetForm() {
        try{
            classCodeText.clear();
            sessionCodeText.clear();
            lessonCodeText.clear();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, String.format("Error in Loading Class Form:%n%s", e.getMessage()), ButtonType.OK);
            alert.show();
        }
    }

    private void showDataOnTable(List<Celass> classesList) {
        ObservableList<Celass> observableList = FXCollections.observableList(classesList);
        classCodeColumn.setCellValueFactory(new PropertyValueFactory<>("class_code"));
        sessionColumn.setCellValueFactory(new PropertyValueFactory<>("session_code"));
        lessonColumn.setCellValueFactory(new PropertyValueFactory<>("lesson_code"));
        teacherColumn.setCellValueFactory(new PropertyValueFactory<>("teacher_code"));
        classTable.setItems(observableList);
    }

    private void selectFromTable() {
        try{
            Celass celass = classTable.getSelectionModel().getSelectedItem();
            classCodeText.setText(String.valueOf(celass.getClassCode()));
            sessionCodeText.setText(String.valueOf(celass.getSession().getSessionCode()));
            lessonCodeText.setText(String.valueOf(celass.getLesson().getLessonCode()));
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Error Loading Data !!!", ButtonType.OK);
            alert.show();
        }
    }

    private void searchByClassCode() {
        try{
            showDataOnTable((List<Celass>) CelassService.getService().findById(Integer.parseInt(searchClassCode.getText())));
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Error Searching Data !!!", ButtonType.OK);
            alert.show();
            log.error("Error Searching Data !!! " + e.getMessage());
        }
    }
}
