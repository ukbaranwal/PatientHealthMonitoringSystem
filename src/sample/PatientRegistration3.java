package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.Window;

import javax.swing.text.html.ImageView;
import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;

public class PatientRegistration3 {
    @FXML
    private ComboBox patient_cigar, patient_alco, patient_activity, patient_food, patient_profession;
    @FXML
    private ImageView Bsecondpage;
    private static String a, b, c, d, e, f, g, h, i, l, m, n, o, p, q, r, s, t, u, v;
    private static Integer j, k;
    @FXML
    private Button submitPatient;
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/userdetails";
    static final String USER = "PHMS";
    static final String PASS = "31101997";
    public void submit(String a, String b, String c, String d, String e, String f, String g, String h, String i, Integer j, Integer k, String l, String m, String n, String o, String p, String q, String r, String s, String t, String u, String v) {
        Connection con = null;
        Statement stmt = null;
        try {
            Class.forName(JDBC_DRIVER);
            con = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = con.createStatement();
            String sql = "INSERT INTO patientdetails " + "VALUES('2','"+a+"','"+b+"','"+c+"','"+d+"','"+e+"','"+f+"','"+g+"','"+h+"','"+i+"','"+j+"','"+k+"','"+l+"','"+m+"','"+n+"','"+o+"','"+p+"','"+q+"','"+r+"','"+s+"','"+t+"','"+u+"','"+v+"')";
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

    public static String getA() {
        return a;
    }

    public static void setA(String a) {
        PatientRegistration3.a = a;
    }

    public static String getB() {
        return b;
    }

    public static void setB(String b) {
        PatientRegistration3.b = b;
    }

    public static String getC() {
        return c;
    }

    public static void setC(String c) {
        PatientRegistration3.c = c;
    }

    public static String getD() {
        return d;
    }

    public static void setD(String d) {
        PatientRegistration3.d = d;
    }

    public static String getE() {
        return e;
    }

    public static void setE(String e) {
        PatientRegistration3.e = e;
    }

    public static String getF() {
        return f;
    }

    public static void setF(String f) {
        PatientRegistration3.f = f;
    }

    public static String getG() {
        return g;
    }

    public static void setG(String g) {
        PatientRegistration3.g = g;
    }

    public static String getH() {
        return h;
    }

    public static void setH(String h) {
        PatientRegistration3.h = h;
    }

    public static String getI() {
        return i;
    }

    public static void setI(String i) {
        PatientRegistration3.i = i;
    }

    public static String getL() {
        return l;
    }

    public static void setL(String l) {
        PatientRegistration3.l = l;
    }

    public static String getM() {
        return m;
    }

    public static void setM(String m) {
        PatientRegistration3.m = m;
    }

    public static String getN() {
        return n;
    }

    public static void setN(String n) {
        PatientRegistration3.n = n;
    }

    public static String getO() {
        return o;
    }

    public static void setO(String o) {
        PatientRegistration3.o = o;
    }

    public static String getP() {
        return p;
    }

    public static void setP(String p) {
        PatientRegistration3.p = p;
    }

    public static String getQ() {
        return q;
    }

    public static void setQ(String q) {
        PatientRegistration3.q = q;
    }

    public static String getR() {
        return r;
    }

    public static void setR(String r) {
        PatientRegistration3.r = r;
    }

    public static String getS() {
        return s;
    }

    public static void setS(String s) {
        PatientRegistration3.s = s;
    }

    public static String getT() {
        return t;
    }

    public static void setT(String t) {
        PatientRegistration3.t = t;
    }

    public static String getU() {
        return u;
    }

    public static void setU(String u) {
        PatientRegistration3.u = u;
    }

    public static String getV() {
        return v;
    }

    public static void setV(String v) {
        PatientRegistration3.v = v;
    }

    public static Integer getJ() {
        return j;
    }

    public static void setJ(Integer j) {
        PatientRegistration3.j = j;
    }

    public static Integer getK() {
        return k;
    }

    public static void setK(Integer k) {
        PatientRegistration3.k = k;
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
    protected void submitForm(ActionEvent event) {
        Window owner = submitPatient.getScene().getWindow();
        r = patient_cigar.getValue().toString();
        s = patient_alco.getValue().toString();
        t = patient_activity.getValue().toString();
        u = patient_food.getValue().toString();
        v = patient_profession.getValue().toString();
        submit(a,b,c,d,e,f,g,h,i,j,k,l,m,n,o,p,q,r,s,t,u,v);
        AlertHelper.showAlert(Alert.AlertType.CONFIRMATION, owner, "Registration Successful!",
                "Welcome ");
    }
    @FXML
    public void closeclick(MouseEvent event) throws IOException {
        System.exit(0);
    }
}
