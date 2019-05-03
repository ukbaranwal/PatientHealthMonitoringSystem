package sample;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.scene.control.cell.PropertyValueFactory;
public class PatientFullDetails implements Initializable {

    @FXML
    private TableView<PatientDetails> tableView;
    @FXML
    private TableColumn<PatientDetails, Integer> Height, Weight, ID;
    @FXML
    private TableColumn<PatientDetails, String> Name, Father, Mobile, Email, Gender, Blood, DOB, Status, Emergency, Address, Allergy, CurMedi, PastMedi, Chronicllness, Injuries, Surgeries, Cigarette, Alcohol, Activity, Food, Profession;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Name.setCellValueFactory(new PropertyValueFactory<PatientDetails, String>("Name"));
        Father.setCellValueFactory(new PropertyValueFactory<PatientDetails, String>("Father"));
        Mobile.setCellValueFactory(new PropertyValueFactory<PatientDetails, String>("Mobile"));
        Gender.setCellValueFactory(new PropertyValueFactory<PatientDetails, String>("Gender"));
        Blood.setCellValueFactory(new PropertyValueFactory<PatientDetails, String>("Blood"));
        Email.setCellValueFactory(new PropertyValueFactory<PatientDetails, String>("Email"));
        DOB.setCellValueFactory(new PropertyValueFactory<PatientDetails, String>("DOB"));
        Status.setCellValueFactory(new PropertyValueFactory<PatientDetails, String>("Status"));
        Emergency.setCellValueFactory(new PropertyValueFactory<PatientDetails, String>("Emergency"));
        Address.setCellValueFactory(new PropertyValueFactory<PatientDetails, String>("Allergy"));
        CurMedi.setCellValueFactory(new PropertyValueFactory<PatientDetails, String>("CurMedi"));
        PastMedi.setCellValueFactory(new PropertyValueFactory<PatientDetails, String>("PastMedi"));
        Chronicllness.setCellValueFactory(new PropertyValueFactory<PatientDetails, String>("ChronicIllness"));
        Injuries.setCellValueFactory(new PropertyValueFactory<PatientDetails, String>("Injuries"));
        Surgeries.setCellValueFactory(new PropertyValueFactory<PatientDetails, String>("Surgeries"));
        Cigarette.setCellValueFactory(new PropertyValueFactory<PatientDetails, String>("Cigarette"));
        Alcohol.setCellValueFactory(new PropertyValueFactory<PatientDetails, String>("Alcohol"));
        Activity.setCellValueFactory(new PropertyValueFactory<PatientDetails, String>("Activity"));
        Food.setCellValueFactory(new PropertyValueFactory<PatientDetails, String>("Food"));
        Profession.setCellValueFactory(new PropertyValueFactory<PatientDetails, String>("Profession"));
        Height.setCellValueFactory(new PropertyValueFactory<PatientDetails, Integer>("Height"));
        Weight.setCellValueFactory(new PropertyValueFactory<PatientDetails, Integer>("Weight"));
        ID.setCellValueFactory(new PropertyValueFactory<PatientDetails, Integer>("ID"));
        tableView.getItems().setAll(parseUserList());
    }

    private List<PatientDetails> parseUserList() {
        List<PatientDetails> list = new ArrayList<PatientDetails>();
        try {
            Connection conn = null;
            Statement stmt = null;
            PatientDetails pd = null;
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost/userdetails", "PHMS", "31101997");
            stmt = conn.createStatement();
            String sql = "SELECT * from patientdetails";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                pd = new PatientDetails(rs.getString("name"), rs.getString("fathername"), rs.getString("contactno"), rs.getString("email"), rs.getString("gender"), rs.getString("dob"), rs.getString("bloodgrp"), rs.getString("maritalstatus"), rs.getInt("height"), rs.getInt("weight"), rs.getString("emergencycontact"), rs.getString("address"), rs.getString("allergies"), rs.getString("curmedication"), rs.getString("pastmedication"), rs.getString("chronicillness"), rs.getString("injuries"), rs.getString("surgeries"), rs.getString("cigarettecon"), rs.getString("alcoholcon"), rs.getString("activitylevel"), rs.getString("foodpreference"), rs.getString("profession"), rs.getInt("id"));
                list.add(pd);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
