package main.java.sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ArchivedAppointments implements Initializable {
    @FXML
    private TableView<PatientAppointmentDetails> tablepatientappointment;
    @FXML
    private TableColumn<PatientAppointmentDetails, Integer> NO, ID;
    @FXML
    private TableColumn<PatientAppointmentDetails, String> Name, Father, Mobile, Reason, Dateappointment, Timeappointment, Doctor, Remark;
    @FXML
    private GridPane appointmentpane;
    @FXML
    private TextField searchName, searchID, searchFather, searchMobile, searchDate;
    @FXML
    private Button btn1, btn2;
    @FXML
    private Label hospitalname;
    private static String searchFlag;
    private static String searchWord;

    public static String getSearchFlag() {
        return searchFlag;
    }

    public static void setSearchFlag(String searchFlag) {
        ArchivedAppointments.searchFlag = searchFlag;
    }

    public static String getSearchWord() {
        return searchWord;
    }

    public static void setSearchWord(String searchWord) {
        ArchivedAppointments.searchWord = searchWord;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        hospitalname.setText(Utilities.HOSPITAL);
        fillTables();
        Utilities.buttonEffect(btn1);
        Utilities.buttonEffect(btn2);
        tablepatientappointment.getItems().setAll(parseUserList());
        MenuItem item1 = new MenuItem("View Details");
        item1.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                try {
                    Stage stage = (Stage) appointmentpane.getScene().getWindow();
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("patient_whole_details.fxml"));
                    PatientWholeDetails controller = fxmlLoader.<PatientWholeDetails>getController();
                    controller.setID(tablepatientappointment.getSelectionModel().getSelectedItem().getID());
                    Scene scene = new Scene(fxmlLoader.load());
                    Stage newWindow = new Stage();
                    newWindow.initStyle(StageStyle.TRANSPARENT);
                    newWindow.setScene(scene);
                    newWindow.initModality(Modality.WINDOW_MODAL);
                    newWindow.initOwner(stage);
                    newWindow.show();
                } catch (Exception ex) {

                }
            }
        });
        final ContextMenu contextMenu = new ContextMenu(item1);
        tablepatientappointment.setContextMenu(contextMenu);
        tablepatientappointment.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getClickCount() == 2) {
                    try {
                        Stage stage = (Stage) appointmentpane.getScene().getWindow();
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("patient_whole_details.fxml"));
                        PatientWholeDetails controller = fxmlLoader.<PatientWholeDetails>getController();
                        controller.setID(tablepatientappointment.getSelectionModel().getSelectedItem().getID());
                        Scene scene = new Scene(fxmlLoader.load());
                        Stage newWindow = new Stage();
                        newWindow.initStyle(StageStyle.TRANSPARENT);
                        newWindow.setScene(scene);
                        newWindow.initModality(Modality.WINDOW_MODAL);
                        newWindow.initOwner(stage);
                        newWindow.show();
                    } catch (Exception e) {

                    }
                }
            }
        });
    }

    @FXML
    public void repopulateTable() {
        fillTables();
        tablepatientappointment.getItems().setAll(searchUserList());
    }

    @FXML
    public void populateTable() {
        fillTables();
        tablepatientappointment.getItems().setAll(parseUserList());
    }

    private List<PatientAppointmentDetails> searchUserList() {
        List<PatientAppointmentDetails> list = new ArrayList<PatientAppointmentDetails>();
        try {
            Connection conn = null;
            Statement stmt = null;
            PatientAppointmentDetails pd = null;
            conn = Utilities.getConnection();
            stmt = conn.createStatement();
            String sql = "SELECT * from archivedappointments where " + ArchivedAppointments.getSearchFlag() + "='" + ArchivedAppointments.getSearchWord() + "';";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                pd = new PatientAppointmentDetails(rs.getInt("number"), rs.getInt("id"), rs.getString("name"), rs.getString("father"), rs.getString("contact"), rs.getString("reason"), rs.getString("appointdate"), rs.getString("appointtime"), rs.getString("doctor"), rs.getString("remarks"));
                list.add(pd);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @FXML
    protected void searchBasedOnName(ActionEvent event) {
        if (searchName.getText().isEmpty()) {
            populateTable();
        } else {
            setSearchWord(searchName.getText().toString());
            setSearchFlag("name");
            repopulateTable();
        }
        searchID.setText("");
        searchDate.setText("");
        searchMobile.setText("");
        searchFather.setText("");
    }

    @FXML
    protected void searchBasedOnID(ActionEvent event) {
        if (searchID.getText().isEmpty()) {
            populateTable();
        } else {
            setSearchWord(searchID.getText().toString());
            setSearchFlag("id");
            repopulateTable();
        }
        searchName.setText("");
        searchDate.setText("");
        searchMobile.setText("");
        searchFather.setText("");
    }

    @FXML
    protected void searchBasedOnFather(ActionEvent event) {
        if (searchFather.getText().isEmpty()) {
            populateTable();
        } else {
            setSearchWord(searchFather.getText().toString());
            setSearchFlag("father");
            repopulateTable();
        }
        searchID.setText("");
        searchDate.setText("");
        searchMobile.setText("");
        searchName.setText("");
    }

    @FXML
    protected void searchBasedOnMobile(ActionEvent event) {
        if (searchMobile.getText().isEmpty()) {
            populateTable();
        } else {
            setSearchWord(searchMobile.getText().toString());
            setSearchFlag("contact");
            repopulateTable();
        }
        searchID.setText("");
        searchDate.setText("");
        searchName.setText("");
        searchFather.setText("");
    }

    @FXML
    protected void searchBasedOnDate(ActionEvent event) {
        if (searchDate.getText().isEmpty()) {
            populateTable();
        } else {
            setSearchWord(searchDate.getText().toString());
            setSearchFlag("appointdate");
            repopulateTable();
        }
        searchID.setText("");
        searchName.setText("");
        searchMobile.setText("");
        searchFather.setText("");
    }

    private List<PatientAppointmentDetails> parseUserList() {
        List<PatientAppointmentDetails> list = new ArrayList<PatientAppointmentDetails>();
        try {
            Connection conn = null;
            Statement stmt = null;
            PatientAppointmentDetails pd = null;
            conn = Utilities.getConnection();
            stmt = conn.createStatement();
            String sql = "SELECT * from archivedappointments";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                pd = new PatientAppointmentDetails(rs.getInt("number"), rs.getInt("id"), rs.getString("name"), rs.getString("father"), rs.getString("contact"), rs.getString("reason"), rs.getString("appointdate"), rs.getString("appointtime"), rs.getString("doctor"), rs.getString("remarks"));
                list.add(pd);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    @FXML
    protected void launchPatientsPage(ActionEvent event){
        try {
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("patient_list.fxml")));
            stage.close();
//            stage.initStyle(StageStyle.TRANSPARENT);
            stage = new Stage();
            stage.setScene(scene);
//            stage.initModality(Modality.WINDOW_MODAL);
//            stage.initOwner(stage);
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    protected void launchAppointmentsPage(ActionEvent event){
        try {
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("appointment_patients.fxml")));
            stage.close();
//            stage.initStyle(StageStyle.TRANSPARENT);
            stage.setScene(scene);
//            stage.initModality(Modality.WINDOW_MODAL);
//            stage.initOwner(stage);
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void fillTables(){
        NO.setCellValueFactory(new PropertyValueFactory<PatientAppointmentDetails, Integer>("NO"));
        Name.setCellValueFactory(new PropertyValueFactory<PatientAppointmentDetails, String>("Name"));
        Father.setCellValueFactory(new PropertyValueFactory<PatientAppointmentDetails, String>("Father"));
        Mobile.setCellValueFactory(new PropertyValueFactory<PatientAppointmentDetails, String>("Mobile"));
        Reason.setCellValueFactory(new PropertyValueFactory<PatientAppointmentDetails, String>("Reason"));
        Dateappointment.setCellValueFactory(new PropertyValueFactory<PatientAppointmentDetails, String>("Dateappointment"));
        Timeappointment.setCellValueFactory(new PropertyValueFactory<PatientAppointmentDetails, String>("Timeappointment"));
        Doctor.setCellValueFactory(new PropertyValueFactory<PatientAppointmentDetails, String>("Doctor"));
        ID.setCellValueFactory(new PropertyValueFactory<PatientAppointmentDetails, Integer>("ID"));
        Remark.setCellValueFactory(new PropertyValueFactory<PatientAppointmentDetails, String>("Remark"));
    }
}
