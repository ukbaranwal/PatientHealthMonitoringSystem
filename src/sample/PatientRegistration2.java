package sample;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.Window;
import java.io.IOException;
import java.time.LocalDate;

public class PatientRegistration2 {
    @FXML
    private ComboBox patient_allergy, patient_curmedi, patient_pastmedi, patient_chronic, patient_injuries, patient_surgeries;
    @FXML
    private ImageView Bthirdpage;
    public static String a, b, c, d, e, f, g, h, i, l, m, n, o, p, q;
    public static Integer j, k;
    public static String getA() {
        return a;
    }

    public static void setA(String a) {
        PatientRegistration2.a = a;
    }

    public static String getB() {
        return b;
    }

    public static void setB(String b) {
        PatientRegistration2.b = b;
    }

    public static String getC() {
        return c;
    }

    public static void setC(String c) {
        PatientRegistration2.c = c;
    }

    public static String getD() {
        return d;
    }

    public static void setD(String d) {
        PatientRegistration2.d = d;
    }

    public static String getE() {
        return e;
    }

    public static void setE(String e) {
        PatientRegistration2.e = e;
    }

    public static String getF() {
        return f;
    }

    public static void setF(String f) {
        PatientRegistration2.f = f;
    }

    public static String getG() {
        return g;
    }

    public static void setG(String g) {
        PatientRegistration2.g = g;
    }

    public static String getH() {
        return h;
    }

    public static void setH(String h) {
        PatientRegistration2.h = h;
    }

    public static String getI() {
        return i;
    }

    public static void setI(String i) {
        PatientRegistration2.i = i;
    }

    public static String getL() {
        return l;
    }

    public static void setL(String l) {
        PatientRegistration2.l = l;
    }

    public static String getM() {
        return m;
    }

    public static void setM(String m) {
        PatientRegistration2.m = m;
    }

    public static String getN() {
        return n;
    }

    public static void setN(String n) {
        PatientRegistration2.n = n;
    }

    public static String getO() {
        return o;
    }

    public static void setO(String o) {
        PatientRegistration2.o = o;
    }

    public static String getP() {
        return p;
    }

    public static void setP(String p) {
        PatientRegistration2.p = p;
    }

    public static String getQ() {
        return q;
    }

    public static void setQ(String q) {
        PatientRegistration2.q = q;
    }

    public static Integer getJ() {
        return j;
    }

    public static void setJ(Integer j) {
        PatientRegistration2.j = j;
    }

    public static Integer getK() {
        return k;
    }

    public static void setK(Integer k) {
        PatientRegistration2.k = k;
    }
//    @FXML
//    protected void launchFirstPage(MouseEvent event) {
//        try {
//            Node node = (Node)event.getSource();
//            Stage stage = (Stage) node.getScene().getWindow();
//            stage.close();
//            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("patient_registration1.fxml")));
//            stage.setScene(scene);
//            stage.show();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
    @FXML
    protected void launchThirdPage(MouseEvent event) {
        try {
            Window owner = ((Node)event.getTarget()).getScene().getWindow();
            m = patient_allergy.getValue().toString();
            n = patient_curmedi.getValue().toString();
            o = patient_pastmedi.getValue().toString();
            p = patient_injuries.getValue().toString();
            q = patient_surgeries.getValue().toString();
            Node node = (Node)event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            stage.close();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("patient_registration3.fxml"));
            PatientRegistration3 controller = fxmlLoader.<PatientRegistration3>getController();
//            controller.setText(a,b,c,d,e,f,g,h,i,j,k,l,m,n,o,p,q);
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
            controller.setM(m);
            controller.setN(n);
            controller.setO(o);
            controller.setP(p);
            controller.setQ(q);
            Scene scene = new Scene(fxmlLoader.load());
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void closeclick(MouseEvent event) throws IOException {
        System.exit(0);
    }
}
