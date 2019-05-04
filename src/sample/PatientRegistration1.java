package sample;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.Window;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.time.LocalDate;

public class PatientRegistration1 {
    @FXML
    private TextField patients_name, patients_father, patients_contact, patients_email, patients_address, patients_blood, patients_marital, patients_height, patients_weight, patients_emergency;
    @FXML
    private Button button_newpatient;
    @FXML
    private ComboBox patients_gender;
    @FXML
    private DatePicker patients_dob;
    private static Integer j, k;
    private static String a, b, c, d, e, f, g, h, i, l;
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/userdetails";
    static final String USER = "PHMS";
    static final String PASS = "31101997";

    @FXML
    protected void launchSecondPage(MouseEvent event) {
        try {
            Window owner = ((Node)event.getTarget()).getScene().getWindow();
            if (patients_name.getText().isEmpty()) {
                AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Error!",
                        "Please enter Patient's Name");
                return;
            }
            if (patients_father.getText().isEmpty()) {
                AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                        "Please enter Patient's Father Name");
                return;
            }
            try{
                LocalDate local = patients_dob.getValue();
                c = local.toString();
            }catch (Exception e){
                c ="n";
            }
            if (c.equals("n")){
                AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                        "Please enter Patient's Date of Birth");
                return;
            }
            if (patients_gender.getValue().toString().equals("Gender")){
                AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                        "Please enter Patient's Gender");
                return;
            }
            if (patients_contact.getText().isEmpty()) {
                AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                        "Please enter Patient's Contact no.");
                return;
            }
            if (patients_email.getText().isEmpty()) {
                AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                        "Please enter Patient's Email ID");
                return;
            }
            if (patients_address.getText().isEmpty()) {
                AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                        "Please enter Patient's Address");
                return;
            }
            if (patients_blood.getText().isEmpty()) {
                AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                        "Please enter Patient's Blood Group");
                return;
            }
            if (patients_marital.getText().isEmpty()) {
                AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                        "Please enter Patient's Marital Status");
                return;
            }
            if (patients_height.getText().isEmpty()) {
                AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                        "Please enter Patient's Height");
                return;
            }
            try{
                Integer.valueOf(patients_height.getText().toString());
            }catch (Exception e){
                AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                        "Please enter Height in Numbers");
                return;
            }
            if (patients_weight.getText().isEmpty()) {
                AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                        "Please enter Patient's Weight");
                return;
            }
            try{
                Integer.valueOf(patients_weight.getText().toString());
            }catch (Exception e){
                AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                        "Please enter Weight in Numbers");
                return;
            }
            if (patients_emergency.getText().isEmpty()) {
                AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                        "Please enter Emergency Contact no.");
                return;
            }
            a = patients_name.getText().toString();
            b = patients_father.getText().toString();
            LocalDate locale = patients_dob.getValue();
            c = locale.toString();
            d = patients_gender.getSelectionModel().getSelectedIndex()+patients_gender.getValue().toString();
            e = patients_contact.getText().toString();
            f = patients_email.getText().toString();
            g = patients_address.getText().toString();
            h = patients_blood.getText().toString();
            i = patients_marital.getText().toString();
            j = Integer.valueOf(patients_height.getText().toString());
            k = Integer.valueOf(patients_weight.getText().toString());
            l = patients_emergency.getText().toString();
            Node node = (Node)event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            stage.close();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("patient_registration2.fxml"));
            PatientRegistration2 controller = fxmlLoader.<PatientRegistration2>getController();
            Scene scene = new Scene(fxmlLoader.load());
            stage.setScene(scene);
            stage.show();
            controller.setA(a);
            controller.setB(b);
            controller.setC(c);
            controller.setD(d);
            controller.setE(e);
            controller.setF(f);
            controller.setG(g);
            controller.setH(h);
            controller.setI(i);
            controller.setJ(j);
            controller.setK(k);
            controller.setL(l);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void closeclick(MouseEvent event) throws IOException {
        System.exit(0);
    }
}

