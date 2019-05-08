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
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AppointmentPatients implements Initializable {
    @FXML
    private TableView<PatientAppointmentDetails> tablepatientappointment;
    @FXML
    private TableColumn<PatientAppointmentDetails, Integer> NO, ID;
    @FXML
    private TableColumn<PatientAppointmentDetails, String> Name, Father, Mobile, Reason, Dateappointment, Timeappointment, Doctor;
    @FXML
    private GridPane appointmentpane;
    @FXML
    private TextField searchName, searchID, searchFather, searchMobile, searchDate;
    private static String searchFlag;
    private static String searchWord;

    public static String getSearchWord() {
        return searchWord;
    }

    public static void setSearchWord(String searchWord) {
        AppointmentPatients.searchWord = searchWord;
    }

    public static String getSearchFlag() {
        return searchFlag;
    }

    public static void setSearchFlag(String searchFlag) {
        AppointmentPatients.searchFlag = searchFlag;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        NO.setCellValueFactory(new PropertyValueFactory<PatientAppointmentDetails, Integer>("NO"));
        Name.setCellValueFactory(new PropertyValueFactory<PatientAppointmentDetails, String>("Name"));
        Father.setCellValueFactory(new PropertyValueFactory<PatientAppointmentDetails, String>("Father"));
        Mobile.setCellValueFactory(new PropertyValueFactory<PatientAppointmentDetails, String>("Mobile"));
        Reason.setCellValueFactory(new PropertyValueFactory<PatientAppointmentDetails, String>("Reason"));
        Dateappointment.setCellValueFactory(new PropertyValueFactory<PatientAppointmentDetails, String>("Dateappointment"));
        Timeappointment.setCellValueFactory(new PropertyValueFactory<PatientAppointmentDetails, String>("Timeappointment"));
        Doctor.setCellValueFactory(new PropertyValueFactory<PatientAppointmentDetails, String>("Doctor"));
        ID.setCellValueFactory(new PropertyValueFactory<PatientAppointmentDetails, Integer>("ID"));
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
        NO.setCellValueFactory(new PropertyValueFactory<PatientAppointmentDetails, Integer>("NO"));
        Name.setCellValueFactory(new PropertyValueFactory<PatientAppointmentDetails, String>("Name"));
        Father.setCellValueFactory(new PropertyValueFactory<PatientAppointmentDetails, String>("Father"));
        Mobile.setCellValueFactory(new PropertyValueFactory<PatientAppointmentDetails, String>("Mobile"));
        Reason.setCellValueFactory(new PropertyValueFactory<PatientAppointmentDetails, String>("Reason"));
        Dateappointment.setCellValueFactory(new PropertyValueFactory<PatientAppointmentDetails, String>("Dateappointment"));
        Timeappointment.setCellValueFactory(new PropertyValueFactory<PatientAppointmentDetails, String>("Timeappointment"));
        Doctor.setCellValueFactory(new PropertyValueFactory<PatientAppointmentDetails, String>("Doctor"));
        ID.setCellValueFactory(new PropertyValueFactory<PatientAppointmentDetails, Integer>("ID"));
        tablepatientappointment.getItems().setAll(searchUserList());
    }

    @FXML
    public void populateTable() {
        NO.setCellValueFactory(new PropertyValueFactory<PatientAppointmentDetails, Integer>("NO"));
        Name.setCellValueFactory(new PropertyValueFactory<PatientAppointmentDetails, String>("Name"));
        Father.setCellValueFactory(new PropertyValueFactory<PatientAppointmentDetails, String>("Father"));
        Mobile.setCellValueFactory(new PropertyValueFactory<PatientAppointmentDetails, String>("Mobile"));
        Reason.setCellValueFactory(new PropertyValueFactory<PatientAppointmentDetails, String>("Reason"));
        Dateappointment.setCellValueFactory(new PropertyValueFactory<PatientAppointmentDetails, String>("Dateappointment"));
        Timeappointment.setCellValueFactory(new PropertyValueFactory<PatientAppointmentDetails, String>("Timeappointment"));
        Doctor.setCellValueFactory(new PropertyValueFactory<PatientAppointmentDetails, String>("Doctor"));
        ID.setCellValueFactory(new PropertyValueFactory<PatientAppointmentDetails, Integer>("ID"));
        tablepatientappointment.getItems().setAll(parseUserList());
    }

    private List<PatientAppointmentDetails> searchUserList() {
        List<PatientAppointmentDetails> list = new ArrayList<PatientAppointmentDetails>();
        try {
            Connection conn = null;
            Statement stmt = null;
            PatientAppointmentDetails pd = null;
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost/userdetails", "PHMS", "31101997");
            stmt = conn.createStatement();
            String sql = "SELECT * from appointments where " + AppointmentPatients.getSearchFlag() + "='" + AppointmentPatients.getSearchWord() + "';";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                pd = new PatientAppointmentDetails(rs.getInt("number"), rs.getInt("id"), rs.getString("name"), rs.getString("father"), rs.getString("contact"), rs.getString("reason"), rs.getString("appointdate"), rs.getString("appointtime"), rs.getString("doctor"));
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
//        tablepatient.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
//            @Override
//            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
//                try{
//                    Stage stage = (Stage)rootpane.getScene().getWindow();
////                    Scene scene = new Scene(FXMLLoader.load(getClass().getResource("patient_whole_details.fxml")));
//                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("patient_whole_details.fxml"));
//                    PatientWholeDetails controller = fxmlLoader.<PatientWholeDetails>getController();
//                    controller.setID(newValue.intValue()+1);
//                    Scene scene = new Scene(fxmlLoader.load());
//                    Stage newWindow = new Stage();
//                    newWindow.initStyle(StageStyle.TRANSPARENT);
//                    newWindow.setScene(scene);
//                    newWindow.initModality(Modality.WINDOW_MODAL);
//                    newWindow.initOwner(stage);
//                    newWindow.show();}
//                catch (Exception e){
//
//                }
//            }
//        });

    private List<PatientAppointmentDetails> parseUserList() {
        List<PatientAppointmentDetails> list = new ArrayList<PatientAppointmentDetails>();
        try {
            Connection conn = null;
            Statement stmt = null;
            PatientAppointmentDetails pd = null;
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost/userdetails", "PHMS", "31101997");
            stmt = conn.createStatement();
            String sql = "SELECT * from appointments";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                pd = new PatientAppointmentDetails(rs.getInt("number"), rs.getInt("id"), rs.getString("name"), rs.getString("father"), rs.getString("contact"), rs.getString("reason"), rs.getString("appointdate"), rs.getString("appointtime"), rs.getString("doctor"));
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
            stage.setScene(scene);
//            stage.initModality(Modality.WINDOW_MODAL);
//            stage.initOwner(stage);
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
