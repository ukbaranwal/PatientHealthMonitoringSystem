package sample;
import com.jfoenix.controls.JFXTextArea;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.Window;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class PatientWholeDetails implements Initializable {
    @FXML
    private JFXTextField patients_wname, patients_wfather, patients_wdob, patients_wgender, patients_wcontact, patients_wemail, patients_waddress, patients_wblood, patients_wmarital, patients_wheight, patients_wweight, patients_wemergency, patients_wallergy, patients_wcurmedi, patients_wpastmedi, patients_wchronic, patients_winjury, patients_wsurgery, patients_wcigar, patients_walco, patients_wactivity, patients_wfood, patients_wprofession;
    @FXML
    private JFXTextArea patients_wextra;
    @FXML
    private Label patients_wid;
    private static Integer j, k;
    private static String a, b, c, d, e, f, g, h, i, l, m, n, o, p, q, r, s, t, u, v, w, x;
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/userdetails";
    static final String USER = "PHMS";
    static final String PASS = "31101997";
    public static int ID;
    public static void setID(int ID) {
        PatientWholeDetails.ID = ID;
    }
    public void setDetails(){
        try {
            Connection conn = null;
            Statement stmt = null;
            PatientDetails pd = null;
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost/userdetails", "PHMS", "31101997");
            stmt = conn.createStatement();
            String sql = "SELECT * from patientdetails where id= "+ID+" ;";
            ResultSet rs = stmt.executeQuery(sql);
            rs.next();
            a=rs.getString("name");
            b=rs.getString("father");
            c=rs.getString("dob");
            d=rs.getString("gender").substring(1);
            e=rs.getString("contact");
            f=rs.getString("email");
            g=rs.getString("address");
            h=rs.getString("blood");
            i=rs.getString("status");
            j=rs.getInt("height");
            k=rs.getInt("weight");
            l=rs.getString("emergency");
            m=rs.getString("curmedi");
            n=rs.getString("pastmedi");
            o=rs.getString("allergy");
            p=rs.getString("chronic");
            q=rs.getString("injuries");
            r=rs.getString("surgeries");
            s=rs.getString("cigar").substring(1);
            t=rs.getString("alcohol").substring(1);
            u=rs.getString("activity").substring(1);
            v=rs.getString("food").substring(1);
            w=rs.getString("profession").substring(1);
            x=rs.getString("extra");
            }catch (Exception e){
                e.printStackTrace();
        }
    }

    @FXML
    protected void launchEditPage(MouseEvent event) {
        try {
            Window owner = ((Node)event.getTarget()).getScene().getWindow();
            Node node = (Node)event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            stage.close();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("patient_edit_details.fxml"));
            PatientEditDetails controller = fxmlLoader.<PatientEditDetails>getController();
            controller.setID(ID);
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            setDetails();
            patients_wid.setText("Patient ID : " + ID);
            patients_wname.setText(a);
            patients_wfather.setText(b);
            patients_wdob.setText(c);
            patients_wgender.setText(d);
            patients_wcontact.setText(e);
            patients_wemail.setText(f);
            patients_waddress.setText(g);
            patients_wblood.setText(h);
            patients_wmarital.setText(i);
            patients_wheight.setText(j.toString());
            patients_wweight.setText(k.toString());
            patients_wemergency.setText(l);
            patients_wcurmedi.setText(m);
            patients_wpastmedi.setText(n);
            patients_wallergy.setText(o);
            patients_wchronic.setText(p);
            patients_winjury.setText(q);
            patients_wsurgery.setText(r);
            patients_wcigar.setText(s);
            patients_walco.setText(t);
            patients_wactivity.setText(u);
            patients_wfood.setText(v);
            patients_wprofession.setText(w);
            patients_wextra.setText(x);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

