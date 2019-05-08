package main.java.sample;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;
import java.util.List;

public class PatientRegistration3 {
    @FXML
    private ComboBox patient_cigar, patient_alco, patient_activity, patient_food, patient_profession;
    @FXML
    private TextArea patient_extra;
    @FXML
    private Label recordconfirm;
    private static String a, b, c, d, e, f, g, h, i, l, m, n, o, p, q, r, s, t, u, v, w, x, y;
    private static Integer j, k, ID;
    static final String PATH = "/Users/utkarsh/MedicalRecords/";
    @FXML
    private Button submitPatient;
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/userdetails";
    static final String USER = "PHMS";
    static final String PASS = "31101997";
    List<File> list = null;
//    @Override
//    public void initialize(URL location, ResourceBundle resources) {
//        try{
//            if(PatientRegistrationDetails.getFlag()==1){
//                patient_cigar.getSelectionModel().select(Integer.parseInt(PatientRegistrationDetails.getD().substring(0,1)));
//                patients_gender.getSelectionModel().select(Integer.parseInt(PatientRegistrationDetails.getD().substring(0,1)));
//            }}
//        catch (Exception e){
//
//        }
//    }
    public void submit(String a, String b, String c, String d, String e, String f, String g, String h, String i, Integer j, Integer k, String l, String m, String n, String o, String p, String q, String r, String s, String t, String u, String v, String w, String x) {
        Connection con = null;
        Statement stmt = null;
        Statement stmt2 = null;
        try {
            Class.forName(JDBC_DRIVER);
            con = DriverManager.getConnection(DB_URL, USER, PASS);
//            String sql2 = "SELECT AUTO_INCREMENT FROM information_schema.TABLES WHERE TABLE_SCHEMA = 'userdetails' AND TABLE_NAME = 'patientdetails';";
//            stmt2 = con.createStatement();
            stmt = con.createStatement();
//            ResultSet rs = stmt2.executeQuery(sql2);
//            rs.next();
//            ID = rs.getInt("AUTO_INCREMENT");
            BufferedReader in = new BufferedReader(new FileReader("/Users/utkarsh/Desktop/github/PatientHealthMonitoringSystem/src/main/java/resources/incrementor.txt"));
            ID = Integer.parseInt(in.readLine());
            System.out.println(ID);
            PrintStream pout = new PrintStream(new FileOutputStream("/Users/utkarsh/Desktop/github/PatientHealthMonitoringSystem/src/main/java/resources/incrementor.txt"));
            pout.println(ID+1);
            try {
                File dir = new File(PATH + "patientID_" + ID);

                dir.mkdir();
                copyFile();
            }catch (Exception ex){

            }
            String sql = "INSERT INTO patientdetails " + "VALUES(0,'"+a+"','"+b+"','"+c+"','"+d+"','"+e+"','"+f+"','"+g+"','"+h+"','"+i+"','"+j+"','"+k+"','"+l+"','"+m+"','"+n+"','"+o+"','"+p+"','"+q+"','"+r+"','"+s+"','"+t+"','"+u+"','"+v+"','"+w+"','"+x+"')";
            stmt.executeUpdate(sql);
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception ex) {
            //Handle errors for Class.forName
            ex.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null)
                    con.close();
            } catch (SQLException se) {
            }// do nothing
            try {
                if (con != null)
                    con.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }
//    @FXML
//    protected void launchSecondPage(MouseEvent event) {
//        try {
//            Node node = (Node)event.getSource();
//            Stage stage = (Stage) node.getScene().getWindow();
//            stage.close();
//            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("patient_registration2.fxml")));
//            stage.setScene(scene);
//            stage.show();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
    @FXML
    protected void submitForm(ActionEvent event) throws Exception {
        Window owner = submitPatient.getScene().getWindow();
        if(patient_cigar.getValue().toString().equals("Cigarette Consumption")){
            s = "";
        }else{
            s = patient_cigar.getSelectionModel().getSelectedIndex()+patient_cigar.getValue().toString();
        }
        if(patient_alco.getValue().toString().equals("Alcohol Consumption")){
            t = "";
        }else{
            t = patient_alco.getSelectionModel().getSelectedIndex()+patient_alco.getValue().toString();
        }
        if(patient_activity.getValue().toString().equals("Activity Level")){
            u = "";
        }else{
            u = patient_activity.getSelectionModel().getSelectedIndex()+patient_activity.getValue().toString();
        }
        if(patient_food.getValue().toString().equals("Food Preference")){
            v = "";
        }else{
            v = patient_food.getSelectionModel().getSelectedIndex()+patient_food.getValue().toString();
        }
        if(patient_profession.getValue().toString().equals("Profession")){
            w = "";
        }else{
            w = patient_profession.getSelectionModel().getSelectedIndex()+patient_profession.getValue().toString();
        }
        x = patient_extra.getText().toString();
        submit(PatientRegistrationDetails.getA(),PatientRegistrationDetails.getB(),PatientRegistrationDetails.getC(),PatientRegistrationDetails.getD(),PatientRegistrationDetails.getE(),PatientRegistrationDetails.getF(),PatientRegistrationDetails.getG(),PatientRegistrationDetails.getH(),PatientRegistrationDetails.getI(),PatientRegistrationDetails.getJ(),PatientRegistrationDetails.getK(),PatientRegistrationDetails.getL(),PatientRegistrationDetails.getM(),PatientRegistrationDetails.getN(),PatientRegistrationDetails.getO(),PatientRegistrationDetails.getP(),PatientRegistrationDetails.getQ(),PatientRegistrationDetails.getR(),s,t,u,v,w,x);
        AlertHelper.showAlert(Alert.AlertType.CONFIRMATION, owner, "", "Patient Added Successfuly!");
        Node node = (Node)event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();
        PatientRegistrationDetails.setFlag(0);
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("patient_list.fxml")));
        stage.setScene(scene);
        stage.show();
    }
    public static void copyFile() throws IOException {
        int count = 0, record = 1;
        for (int count2 = 0; count2 < y.length(); count2++) {
            if (y.charAt(count2) == ',') {
                Path src = Paths.get(y.substring(count, count2));
                Path dest = Paths.get(PATH+"patientID_" + ID + "/medicalrecord" + record+".jpg");
                Files.copy(src, dest);
                record++;
                count=count2+2;
            }
        }
    }
    @FXML
    public void closeclick(MouseEvent event) throws IOException {
        Window owner = ((Node)event.getTarget()).getScene().getWindow();
        Node node = (Node)event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();
    }
    @FXML
    protected void addMedicalRecords() {
        FileChooser fileChooser = new FileChooser();
        //Set extension filter
        FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
        fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);
        y = "";
        list = fileChooser.showOpenMultipleDialog(null);
        try {
            if(!list.isEmpty()) {
                for (File file : list) {
                    y = y + file.getAbsolutePath().toString() + ", ";
                }
                recordconfirm.setText(list.size()+" Records Added");
            }
        }catch (Exception e){

        }
    }
}

