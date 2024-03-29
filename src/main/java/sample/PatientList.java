package main.java.sample;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
/*
    Created by: Utkarsh Baranwal
 */
public class PatientList implements Initializable {

    @FXML
    private TableView<PatientDetails> tablepatient;
    @FXML
    private TableColumn<PatientDetails, Integer> ID;
    @FXML
    private TableColumn<PatientDetails, String> Name, Father, Mobile, Email, Gender, DOB, Emergency, Address;
    @FXML
    private GridPane rootpane;

    public GridPane getRootpane() {
        return rootpane;
    }

    public void setRootpane(GridPane rootpane) {
        this.rootpane = rootpane;
    }

    @FXML
    private Label hospitalname;
    @FXML
    private TextField searchName, searchID, searchFather, searchMobile, searchBlood;
    @FXML
    private Button btn_newpatient, btn_reminder, btn_appoint, btn_logout;
    private static String searchFlag;
    private static String searchWord;

    public static String getSearchWord() {
        return searchWord;
    }

    public static void setSearchWord(String searchWord) {
        PatientList.searchWord = searchWord;
    }

    public static String getSearchFlag() {
        return searchFlag;
    }

    public static void setSearchFlag(String searchFlag) {
        PatientList.searchFlag = searchFlag;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        hospitalname.setText(Utilities.HOSPITAL);

        fillTables();
        Utilities.buttonEffect(btn_newpatient);
        Utilities.buttonEffect(btn_reminder);
        Utilities.buttonEffect(btn_appoint);
        Utilities.buttonEffect(btn_logout);
        tablepatient.getItems().setAll(parseUserList());
        MenuItem item1 = new MenuItem("View Details");
        item1.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                try {
                    Utilities.event=e;
                    Stage stage = (Stage) rootpane.getScene().getWindow();
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("patient_whole_details.fxml"));
                    PatientWholeDetails controller = fxmlLoader.<PatientWholeDetails>getController();
                    controller.setID(tablepatient.getSelectionModel().getSelectedItem().getID());
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
        MenuItem item2 = new MenuItem("Create an Appointment");
        item2.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                try {
                    Utilities.event=e;
                    Stage stage = (Stage) rootpane.getScene().getWindow();
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("create_appointment.fxml"));
                    CreateAppointment controller = fxmlLoader.<CreateAppointment>getController();
                    controller.setID(tablepatient.getSelectionModel().getSelectedItem().getID());
                    controller.setName(tablepatient.getSelectionModel().getSelectedItem().getName());
                    controller.setContact(tablepatient.getSelectionModel().getSelectedItem().getMobile());
                    controller.setFather(tablepatient.getSelectionModel().getSelectedItem().getFather());
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
        MenuItem item3 = new MenuItem("Patient Analysis");
        item3.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                try {
                    PatientHealthAnalysis.setID(tablepatient.getSelectionModel().getSelectedItem().getID());
                    PatientHealthAnalysis.runAnalysis();
                } catch (Exception ex) {

                }
            }
        });
        final ContextMenu contextMenu = new ContextMenu(item1, item2, item3);
        tablepatient.setContextMenu(contextMenu);
        tablepatient.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getClickCount() == 2) {
                    try {
                        Utilities.event=event;
                        Stage stage = (Stage) rootpane.getScene().getWindow();
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("patient_whole_details.fxml"));
                        PatientWholeDetails controller = fxmlLoader.<PatientWholeDetails>getController();
                        controller.setID(tablepatient.getSelectionModel().getSelectedItem().getID());
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
        tablepatient.getItems().setAll(searchUserList());
    }

    @FXML
    public void populateTable() {
        fillTables();
        tablepatient.getItems().setAll(parseUserList());
    }

    private List<PatientDetails> searchUserList() {
        List<PatientDetails> list = new ArrayList<PatientDetails>();
        try {
            Connection conn = null;
            Statement stmt = null;
            PatientDetails pd = null;
            conn = Utilities.getConnection();
            stmt = conn.createStatement();
            String sql = "SELECT * from patientdetails where " + PatientList.getSearchFlag() + "='" + PatientList.getSearchWord() + "';";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                pd = new PatientDetails(rs.getString("name"), rs.getString("father"), rs.getString("contact"), rs.getString("email"), rs.getString("gender").substring(1), rs.getString("dob"), rs.getString("emergency"), rs.getString("address"), rs.getInt("id"));
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
        searchBlood.setText("");
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
        searchBlood.setText("");
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
        searchBlood.setText("");
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
        searchBlood.setText("");
        searchName.setText("");
        searchFather.setText("");
    }

    @FXML
    protected void searchBasedOnBlood(ActionEvent event) {
        if (searchBlood.getText().isEmpty()) {
            populateTable();
        } else {
            setSearchWord(searchBlood.getText().toString());
            setSearchFlag("blood");
            repopulateTable();
        }
        searchID.setText("");
        searchName.setText("");
        searchMobile.setText("");
        searchFather.setText("");
    }

    private List<PatientDetails> parseUserList() {
        List<PatientDetails> list = new ArrayList<PatientDetails>();
        try {
            Connection conn = null;
            Statement stmt = null;
            PatientDetails pd = null;
            conn = Utilities.getConnection();
            stmt = conn.createStatement();
            String sql = "SELECT * from patientdetails";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                pd = new PatientDetails(rs.getString("name"), rs.getString("father"), rs.getString("contact"), rs.getString("email"), rs.getString("gender").substring(1), rs.getString("dob"), rs.getString("emergency"), rs.getString("address"), rs.getInt("id"));
                list.add(pd);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @FXML
    protected void addNewPatient(ActionEvent event) {
        try {
            Utilities.event=event;
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("patient_registration1.fxml")));
            Stage newWindow = new Stage();
            newWindow.initStyle(StageStyle.TRANSPARENT);
            newWindow.setScene(scene);
            newWindow.initModality(Modality.WINDOW_MODAL);
            newWindow.initOwner(stage);
            newWindow.show();

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
            stage = new Stage();
            stage.setScene(scene);
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    protected void sendreminders(ActionEvent event){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDate localDate = LocalDate.now();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Do you want to send reminders to the patients having today's appointment", ButtonType.CANCEL, ButtonType.YES);
        if(alert.getResult()==ButtonType.CANCEL){
            return;
        }
        alert.showAndWait();
        List<PatientAppointmentDetails> list = new ArrayList<PatientAppointmentDetails>();
        try {
            Connection conn = null;
            Statement stmt = null;
            PatientAppointmentDetails pd = null;
            conn = Utilities.getConnection();
            stmt = conn.createStatement();
            String sql = "SELECT * from appointments where appointdate='" +localDate.toString()+ "';";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                CreateAppointment.sendMessage(rs.getString("contact"),rs.getString("message"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    @FXML
    private void fillTables(){
        Name.setCellValueFactory(new PropertyValueFactory<PatientDetails, String>("Name"));
        Father.setCellValueFactory(new PropertyValueFactory<PatientDetails, String>("Father"));
        Mobile.setCellValueFactory(new PropertyValueFactory<PatientDetails, String>("Mobile"));
        Gender.setCellValueFactory(new PropertyValueFactory<PatientDetails, String>("Gender"));
        Email.setCellValueFactory(new PropertyValueFactory<PatientDetails, String>("Email"));
        DOB.setCellValueFactory(new PropertyValueFactory<PatientDetails, String>("DOB"));
        Emergency.setCellValueFactory(new PropertyValueFactory<PatientDetails, String>("Emergency"));
        Address.setCellValueFactory(new PropertyValueFactory<PatientDetails, String>("Address"));
        ID.setCellValueFactory(new PropertyValueFactory<PatientDetails, Integer>("ID"));
    }
    @FXML
    private void LogOut(ActionEvent event){
        try {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to Logout", ButtonType.CANCEL, ButtonType.YES);
            alert.showAndWait();
            if(alert.getResult()==ButtonType.CANCEL){
                return;
            }
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("login_form.fxml")));
            stage.close();
            stage = new Stage();
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.setScene(scene);
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
/*
    Created by: Utkarsh Baranwal
 */