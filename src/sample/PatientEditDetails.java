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
import java.sql.*;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class PatientEditDetails implements Initializable {
    @FXML
    private JFXTextField patients_ename, patients_efather, patients_edob, patients_econtact, patients_eemail, patients_eaddress, patients_eblood, patients_emarital, patients_eheight, patients_eweight, patients_eemergency, patients_eallergy, patients_ecurmedi, patients_epastmedi, patients_echronic, patients_einjury, patients_esurgery;
    @FXML
    private ComboBox patients_egender, patients_ecigar, patients_ealco, patients_eactivity, patients_efood, patients_eprofession;
    @FXML
    private JFXTextArea patients_eextra;
    @FXML
    private Label patients_eid;
    private static Integer j, k;
    private static String a, b, c, d, e, f, g, h, i, l, m, n, o, p, q, r, s, t, u, v, w, x;
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/userdetails";
    static final String USER = "PHMS";
    static final String PASS = "31101997";
    public static int ID;
    public static void setID(int ID) {
        PatientEditDetails.ID = ID;
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
            d=rs.getString("gender").substring(0,1);
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
            s=rs.getString("cigar").substring(0,1);
            t=rs.getString("alcohol").substring(0,1);
            u=rs.getString("activity").substring(0,1);
            v=rs.getString("food").substring(0,1);
            w=rs.getString("profession").substring(0,1);
            x=rs.getString("extra");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

//    @FXML
//    protected void launchEditPage(MouseEvent event) {
//        try {
//            Window owner = ((Node)event.getTarget()).getScene().getWindow();
//            Node node = (Node)event.getSource();
//            Stage stage = (Stage) node.getScene().getWindow();
//            stage.close();
//            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("patient_edit_details.fxml"));
//            PatientEditDetails controller = fxmlLoader.<PatientEditDetails>getController();
//            Scene scene = new Scene(fxmlLoader.load());
//            stage.setScene(scene);
//            stage.show();
//            controller.setID(ID);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
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
            patients_eid.setText("Patient ID : " + ID);
            patients_ename.setText(a);
            patients_efather.setText(b);
            patients_edob.setText(c);
            patients_egender.getSelectionModel().select(Integer.parseInt(d));
            patients_econtact.setText(e);
            patients_eemail.setText(f);
            patients_eaddress.setText(g);
            patients_eblood.setText(h);
            patients_emarital.setText(i);
            patients_eheight.setText(j.toString());
            patients_eweight.setText(k.toString());
            patients_eemergency.setText(l);
            patients_ecurmedi.setText(m);
            patients_epastmedi.setText(n);
            patients_eallergy.setText(o);
            patients_echronic.setText(p);
            patients_einjury.setText(q);
            patients_esurgery.setText(r);
            patients_ecigar.getSelectionModel().select(Integer.parseInt(s));
            patients_ealco.getSelectionModel().select(Integer.parseInt(t));
            patients_eactivity.getSelectionModel().select(Integer.parseInt(u));
            patients_efood.getSelectionModel().select(Integer.parseInt(v));
            patients_eprofession.getSelectionModel().select(Integer.parseInt(w));
            patients_eextra.setText(x);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @FXML
    protected void saveDetails(MouseEvent event){
        try {
            Window owner = ((Node)event.getTarget()).getScene().getWindow();
            if (patients_ename.getText().isEmpty()) {
                AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Error!",
                        "Please enter Patient's Name");
                return;
            }
            if (patients_efather.getText().isEmpty()) {
                AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                        "Please enter Patient's Father Name");
                return;
            }

            if (patients_edob.getText().isEmpty()){
                AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                        "Please enter Patient's Date of Birth");
                return;
            }
            if (patients_egender.getValue().toString().equals("Gender")){
                AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                        "Please enter Patient's Gender");
                return;
            }
            if (patients_econtact.getText().isEmpty()) {
                AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                        "Please enter Patient's Contact no.");
                return;
            }
            if (patients_eemail.getText().isEmpty()) {
                AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                        "Please enter Patient's Email ID");
                return;
            }
            if (patients_eaddress.getText().isEmpty()) {
                AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                        "Please enter Patient's Address");
                return;
            }
            if (patients_eblood.getText().isEmpty()) {
                AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                        "Please enter Patient's Blood Group");
                return;
            }
            if (patients_emarital.getText().isEmpty()) {
                AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                        "Please enter Patient's Marital Status");
                return;
            }
            if (patients_eheight.getText().isEmpty()) {
                AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                        "Please enter Patient's Height");
                return;
            }
            try{
                Integer.valueOf(patients_eheight.getText().toString());
            }catch (Exception e){
                AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                        "Please enter Height in Numbers");
                return;
            }
            if (patients_eweight.getText().isEmpty()) {
                AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                        "Please enter Patient's Weight");
                return;
            }
            try{
                Integer.valueOf(patients_eweight.getText().toString());
            }catch (Exception e){
                AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                        "Please enter Weight in Numbers");
                return;
            }
            if (patients_eemergency.getText().isEmpty()) {
                AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                        "Please enter Emergency Contact no.");
                return;
            }
            a=patients_ename.getText();
            b=patients_efather.getText();
            c=patients_edob.getText();
            d=patients_egender.getSelectionModel().getSelectedIndex()+patients_egender.getValue().toString();
            e=patients_econtact.getText();
            f=patients_eemail.getText();
            g=patients_eaddress.getText();
            h=patients_eblood.getText();
            j=Integer.valueOf(patients_eheight.getText().toString());
            k=Integer.valueOf(patients_eweight.getText().toString());
            l=patients_eemergency.getText();
            m=patients_ecurmedi.getText();
            n=patients_epastmedi.getText();
            o=patients_eallergy.getText();
            p=patients_echronic.getText();
            q=patients_einjury.getText();
            r=patients_esurgery.getText();
            s=patients_ecigar.getSelectionModel().getSelectedIndex()+patients_ecigar.getValue().toString();
            t=patients_ealco.getSelectionModel().getSelectedIndex()+patients_ealco.getValue().toString();
            u=patients_eactivity.getSelectionModel().getSelectedIndex()+patients_eactivity.getValue().toString();
            v=patients_efood.getSelectionModel().getSelectedIndex()+patients_efood.getValue().toString();
            w=patients_eprofession.getSelectionModel().getSelectedIndex()+patients_eprofession.getValue().toString();
            x=patients_eextra.getText();
            update(a,b,c,d,e,f,g,h,i,j,k,l,m,n,o,p,q,r,s,t,u,v,w,x);
            AlertHelper.showAlert(Alert.AlertType.CONFIRMATION, owner, "",
                    "Patient Added Successfuly!");
            Node node = (Node)event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            stage.close();
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("patient_list.fxml")));
            stage.setScene(scene);
            stage.show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void update(String a, String b, String c, String d, String e, String f, String g, String h, String i, Integer j, Integer k, String l, String m, String n, String o, String p, String q, String r, String s, String t, String u, String v, String w, String x) {
        Connection con = null;
        Statement stmt = null;
        try {
            Class.forName(JDBC_DRIVER);
            con = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = con.createStatement();
            String sql = "UPDATE patientdetails " + "SET name='"+a+"', father='"+b+"', dob='"+c+"', gender='"+d+"', contact='"+e+"', email='"+f+"', address='"+g+"', blood='"+h+"', status='"+i+"', height='"+j+"', weight='"+k+"', emergency='"+l+"', curmedi='"+m+"', pastmedi='"+n+"', allergy='"+o+"', chronic='"+p+"', injuries='"+q+"', surgeries='"+r+"', cigar='"+s+"', alcohol='"+t+"', activity='"+u+"', food='"+v+"', profession='"+w+"', extra='"+x+"' where id="+ID;
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
}

