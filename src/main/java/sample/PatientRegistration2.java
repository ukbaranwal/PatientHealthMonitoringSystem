package main.java.sample;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.Window;
import org.controlsfx.control.CheckComboBox;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Observable;
import java.util.ResourceBundle;

public class PatientRegistration2 {
    @FXML
    private ImageView Bthirdpage;
    @FXML
    private TextField patients_curmedi, patients_pastmedi;
    @FXML
    private CheckComboBox patients_allergy, patients_chronic, patients_injuries, patients_surgeries;
    public static String a, b, c, d, e, f, g, h, i, l, m, n, o, p, q, r;
    public static Integer j, k;

//    @Override
//    public void initialize(URL location, ResourceBundle resources) {
//        try{
//            if(PatientRegistrationDetails.getFlag()==1){
//                patients_allergy.setCheckModel(patients_allergy.getCheckModel());
//                        getCheckModel().getCheckedIndices();
//                patients_curmedi.setText(PatientRegistrationDetails.getN());
//                patients_pastmedi.setText(PatientRegistrationDetails.getO());
//                patients_chronic;
//                patients_injuries;
//                patients_surgeries;

//            }}
//        catch (Exception e){

//        }
//    }
    @FXML
    protected void launchFirstPage(MouseEvent event) {
        try {
            Node node = (Node)event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            stage.close();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("patient_registration1.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    protected void launchThirdPage(MouseEvent event) {
        try {
            Window owner = ((Node)event.getTarget()).getScene().getWindow();
            m = patients_allergy.getCheckModel().getCheckedItems().toString();
            n = patients_curmedi.getText().toString();;
            o = patients_pastmedi.getText().toString();
            p = patients_chronic.getCheckModel().getCheckedItems().toString();
            q = patients_injuries.getCheckModel().getCheckedItems().toString();
            r = patients_surgeries.getCheckModel().getCheckedItems().toString();
            m = m.substring(1,m.length()-1);
            p = p.substring(1,p.length()-1);
            q = q.substring(1,q.length()-1);
            r = r.substring(1,r.length()-1);
            Node node = (Node)event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            stage.close();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("patient_registration3.fxml"));
            PatientRegistrationDetails controller = fxmlLoader.<PatientRegistrationDetails>getController();
            controller.setM(m);
            controller.setN(n);
            controller.setO(o);
            controller.setP(p);
            controller.setQ(q);
            controller.setR(r);
            Scene scene = new Scene(fxmlLoader.load());
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void closeclick(MouseEvent event) throws IOException {
        Window owner = ((Node)event.getTarget()).getScene().getWindow();
        Node node = (Node)event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();
    }
}
