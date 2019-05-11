package main.java.sample;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.Window;
import org.controlsfx.control.CheckComboBox;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
/*
    Created by: Utkarsh Baranwal
 */
public class PatientRegistration2 implements Initializable{
    @FXML
    private ImageView img, img2, img1;
    @FXML
    private TextField patients_curmedi, patients_pastmedi;
    @FXML
    private CheckComboBox patients_allergy, patients_chronic, patients_injuries, patients_surgeries;
    public static String a, b, c, d, e, f, g, h, i, l, m, n, o, p, q, r;
    public static Integer j, k;

    @FXML
    protected void launchFirstPage(MouseEvent event) {
        try {
            Node node = (Node)event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            stage.close();
            stage = new Stage();
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
        Node node = (Node)event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Utilities.buttonEffect(img);
        Utilities.cursorEffect(img1);
        Utilities.cursorEffect(img2);
    }
}
/*
    Created by: Utkarsh Baranwal
 */
