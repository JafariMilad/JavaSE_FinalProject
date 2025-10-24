package educationalSystem.controller;

import educationalSystem.model.service.LessonService;
import educationalSystem.model.service.PaymentService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lombok.extern.log4j.Log4j2;
import educationalSystem.model.entity.Enrollment;
import educationalSystem.model.entity.enums.EnrollmentStatus;
import educationalSystem.model.service.EnrollmentService;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

@Log4j2
public class EnrollmentController implements Initializable {

    @FXML
    private TextField  enrollmentCodeText, lessonCodeText, paymentCodeText, searchEnrollmentCode;

    @FXML
    private ComboBox<EnrollmentStatus> statusCombo;

    @FXML
    private DatePicker registerDate;

    @FXML
    private Button saveButton, editButton, deleteButton;

    @FXML
    private TableView<Enrollment> enrollmentTable;

    @FXML
    private TableColumn<Enrollment, String> statusColumn;

    @FXML
    private TableColumn<Enrollment, Integer> codeColumn, lessonColumn, teacherColumn, studentColumn, paymentColumn;

    @FXML
    private TableColumn<Enrollment, LocalDate> dateColumn;

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
                Enrollment enrollment =
                        Enrollment
                                .builder()
                                .enrollmentCode(Integer.parseInt(enrollmentCodeText.getText()))
                                .enrollmentStatus(statusCombo.getSelectionModel().getSelectedItem())
                                .lesson(LessonService.getService().findById(Integer.parseInt(lessonCodeText.getText())))
                                .registerDate(registerDate.getValue())
                                .payment(PaymentService.getService().findById(Integer.parseInt(paymentCodeText.getText())))
                                .build();
                EnrollmentService.getService().save(enrollment);
                log.info("Successfully Saved Enrollment");
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Successfully Saved Enrollment\n" + enrollment, ButtonType.OK);
                alert.show();
                resetForm();
            } catch (Exception e) {
                log.error("Error Saving Data", e.getMessage());
                Alert alert = new Alert(Alert.AlertType.ERROR, "Error Saving Data !!!" + e.getMessage(), ButtonType.OK);
                alert.show();
            }
        });
        
        editButton.setOnAction(event -> {
            try {
                Enrollment enrollment =
                        Enrollment
                                .builder()
                                .enrollmentCode(Integer.parseInt(enrollmentCodeText.getText()))
                                .enrollmentStatus(statusCombo.getSelectionModel().getSelectedItem())
                                .lesson(LessonService.getService().findById(Integer.parseInt(lessonCodeText.getText())))
                                .registerDate(registerDate.getValue())
                                .payment(PaymentService.getService().findById(Integer.parseInt(paymentCodeText.getText())))
                                .build();
                EnrollmentService.getService().edit(enrollment);
                log.info("Successfully Saved Enrollment");
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Edited Successfully\n" + enrollment, ButtonType.OK);
                alert.show();
                resetForm();
            } catch (Exception e) {
                log.error("Error Saving Data", e.getMessage());
                Alert alert = new Alert(Alert.AlertType.ERROR, "Error Saving Data !!!" + e.getMessage(), ButtonType.OK);
                alert.show();
            }
        });
        
        deleteButton.setOnAction(event -> {
            try{
                EnrollmentService.getService().delete(Integer.parseInt(enrollmentCodeText.getText()));
                log.info("Successfully Deleted Enrollment");
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Deleted Successfully\n" + enrollmentCodeText.getText(), ButtonType.OK);
                alert.show();
                resetForm();
            } catch (Exception e) {
                log.error("Error Deleting Data", e.getMessage());
                Alert alert = new Alert(Alert.AlertType.ERROR, "Error Deleting Data !!! " + e.getMessage(), ButtonType.OK);
                alert.show();
            }
        });
        
        searchEnrollmentCode.setOnAction(event -> searchByEnrollmentCode());
        
        enrollmentTable.setOnMouseReleased(event -> selectFromTable());
        
        enrollmentTable.setOnKeyReleased(event -> selectFromTable());
    }

    private void resetForm() {
        try{
            enrollmentCodeText.clear();
            statusCombo.getItems().clear();
            for (EnrollmentStatus status : EnrollmentStatus.values()) {
                statusCombo.getItems().add(status);
            }
            statusCombo.getSelectionModel().select(0);
            lessonCodeText.clear();
            registerDate.setValue(LocalDate.now());
            paymentCodeText.clear();
            searchEnrollmentCode.clear();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, String.format("Error in Loading Enrollment Form:%n%s", e.getMessage()), ButtonType.OK);
            alert.show();
        }
    }
    
    private void showDataOnTable(List<Enrollment> enrollmentsList) {
        ObservableList<Enrollment> observableList = FXCollections.observableArrayList(enrollmentsList);
        codeColumn.setCellValueFactory(new PropertyValueFactory<>("enrollmentCode"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("enrollmentStatus"));
        lessonColumn.setCellValueFactory(new PropertyValueFactory<>("lesson"));
        teacherColumn.setCellValueFactory(new PropertyValueFactory<>("teacher"));
        studentColumn.setCellValueFactory(new PropertyValueFactory<>("student"));
        paymentColumn.setCellValueFactory(new PropertyValueFactory<>("payment"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("registerDate"));
        enrollmentTable.setItems(observableList);
    }
    
    private void selectFromTable() {
        try{
            Enrollment enrollment = enrollmentTable.getSelectionModel().getSelectedItem();
            enrollmentCodeText.setText(String.valueOf(enrollment.getEnrollmentCode()));
            statusCombo.getSelectionModel().select(enrollment.getEnrollmentStatus());
            lessonCodeText.setText(String.valueOf(enrollment.getLesson().getLessonCode()));
            registerDate.setValue(LocalDate.now());
            paymentCodeText.setText(String.valueOf(enrollment.getPayment().getPaymentCode()));
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Error Loading Data !!!", ButtonType.OK);
            alert.show();
        }
    }

    private void searchByEnrollmentCode() {
        try{
            showDataOnTable((List<Enrollment>) EnrollmentService.getService().findById(Integer.parseInt(searchEnrollmentCode.getText())));
            log.info("Successfully Searched By Enrollment Code" + searchEnrollmentCode.getText());
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Error Searching Data !!!", ButtonType.OK);
            alert.show();
            log.error("Error Searching Data !!! " + e.getMessage());
        }
    }
}
