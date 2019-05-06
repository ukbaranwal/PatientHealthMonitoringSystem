package sample;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
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

import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;
import org.omg.Messaging.SYNC_WITH_TRANSPORT;

public class PatientsList implements Initializable {

    @FXML
    private TableView<PatientDetails> tablepatient;
    @FXML
    private TableColumn<PatientDetails, Integer> ID;
    @FXML
    private TableColumn<PatientDetails, String> Name, Father, Mobile, Email, Gender, DOB, Emergency, Address;
    @FXML
    private GridPane rootpane;
    @FXML
    private TextField searchName, searchID, searchFather, searchMobile, searchBlood;
    private static String searchFlag;
    private static String searchWord;

    public static String getSearchWord() {
        return searchWord;
    }

    public static void setSearchWord(String searchWord) {
        PatientsList.searchWord = searchWord;
    }

    public static String getSearchFlag() {
        return searchFlag;
    }

    public static void setSearchFlag(String searchFlag) {
        PatientsList.searchFlag = searchFlag;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources){
        Name.setCellValueFactory(new PropertyValueFactory<PatientDetails, String>("Name"));
        Father.setCellValueFactory(new PropertyValueFactory<PatientDetails, String>("Father"));
        Mobile.setCellValueFactory(new PropertyValueFactory<PatientDetails, String>("Mobile"));
        Gender.setCellValueFactory(new PropertyValueFactory<PatientDetails, String>("Gender"));
        Email.setCellValueFactory(new PropertyValueFactory<PatientDetails, String>("Email"));
        DOB.setCellValueFactory(new PropertyValueFactory<PatientDetails, String>("DOB"));
        Emergency.setCellValueFactory(new PropertyValueFactory<PatientDetails, String>("Emergency"));
        Address.setCellValueFactory(new PropertyValueFactory<PatientDetails, String>("Address"));
        ID.setCellValueFactory(new PropertyValueFactory<PatientDetails, Integer>("ID"));
        tablepatient.getItems().setAll(parseUserList());
        tablepatient.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getClickCount() == 2) {
                    try {
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
    public void repopulateTable(){
        Name.setCellValueFactory(new PropertyValueFactory<PatientDetails, String>("Name"));
        Father.setCellValueFactory(new PropertyValueFactory<PatientDetails, String>("Father"));
        Mobile.setCellValueFactory(new PropertyValueFactory<PatientDetails, String>("Mobile"));
        Gender.setCellValueFactory(new PropertyValueFactory<PatientDetails, String>("Gender"));
        Email.setCellValueFactory(new PropertyValueFactory<PatientDetails, String>("Email"));
        DOB.setCellValueFactory(new PropertyValueFactory<PatientDetails, String>("DOB"));
        Emergency.setCellValueFactory(new PropertyValueFactory<PatientDetails, String>("Emergency"));
        Address.setCellValueFactory(new PropertyValueFactory<PatientDetails, String>("Address"));
        ID.setCellValueFactory(new PropertyValueFactory<PatientDetails, Integer>("ID"));
        tablepatient.getItems().setAll(searchUserList());
    }
    @FXML
    public void populateTable(){
        Name.setCellValueFactory(new PropertyValueFactory<PatientDetails, String>("Name"));
        Father.setCellValueFactory(new PropertyValueFactory<PatientDetails, String>("Father"));
        Mobile.setCellValueFactory(new PropertyValueFactory<PatientDetails, String>("Mobile"));
        Gender.setCellValueFactory(new PropertyValueFactory<PatientDetails, String>("Gender"));
        Email.setCellValueFactory(new PropertyValueFactory<PatientDetails, String>("Email"));
        DOB.setCellValueFactory(new PropertyValueFactory<PatientDetails, String>("DOB"));
        Emergency.setCellValueFactory(new PropertyValueFactory<PatientDetails, String>("Emergency"));
        Address.setCellValueFactory(new PropertyValueFactory<PatientDetails, String>("Address"));
        ID.setCellValueFactory(new PropertyValueFactory<PatientDetails, Integer>("ID"));
        tablepatient.getItems().setAll(parseUserList());
    }
    private List<PatientDetails> searchUserList() {
        List<PatientDetails> list = new ArrayList<PatientDetails>();
        try {
            Connection conn = null;
            Statement stmt = null;
            PatientDetails pd = null;
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost/userdetails", "PHMS", "31101997");
            stmt = conn.createStatement();
            String sql = "SELECT * from patientdetails where "+PatientsList.getSearchFlag()+"='"+PatientsList.getSearchWord()+"';";
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
    protected void searchBasedOnName(ActionEvent event){
        if(searchName.getText().isEmpty()){
            populateTable();
        }else {
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
    protected void searchBasedOnID(ActionEvent event){
        if(searchID.getText().isEmpty()){
            populateTable();
        }else {
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
    protected void searchBasedOnFather(ActionEvent event){
        if(searchFather.getText().isEmpty()){
            populateTable();
        }else {
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
    protected void searchBasedOnMobile(ActionEvent event){
        if(searchMobile.getText().isEmpty()){
            populateTable();
        }else {
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
    protected void searchBasedOnBlood(ActionEvent event){
        if(searchBlood.getText().isEmpty()){
            populateTable();
        }else {
            setSearchWord(searchBlood.getText().toString());
            setSearchFlag("blood");
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
                pd = new PatientDetails(rs.getString("name"), rs.getString("father"), rs.getString("contact"), rs.getString("email"), rs.getString("gender").substring(1), rs.getString("dob"), rs.getString("emergency"), rs.getString("address"), rs.getInt("id"));
                list.add(pd);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @FXML
    protected void addNewPatient(ActionEvent event){
        try {
            Node node = (Node)event.getSource();
            Stage stage = (Stage)node.getScene().getWindow();
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

}
