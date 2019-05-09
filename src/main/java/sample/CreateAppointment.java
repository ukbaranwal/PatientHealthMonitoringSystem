package main.java.sample;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTimePicker;
import com.sun.org.apache.xml.internal.security.Init;
import com.twilio.Twilio;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.Window;
import com.twilio.rest.api.v2010.account.Message;

import java.io.*;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ResourceBundle;

public class CreateAppointment implements Initializable {
    @FXML
    private JFXDatePicker appointdate;
    @FXML
    private JFXTimePicker appointtime;
    @FXML
    private JFXTextArea appointreason;
    @FXML
    private JFXTextField appointdoctor;
    @FXML
    private Button btn;
    @FXML
    private ImageView img;
    public static int ID;
    public static String name, contact, father;
    public static final String ACCOUNT_SID = "AC304fa123df9a164738a7ee9799e667d3";
    public static final String AUTH_TOKEN = "c937ae9bb30217f676604bc79fc04d23";
    public static int getID() {
        return ID;
    }

    public static void setID(int ID) {
        CreateAppointment.ID = ID;
    }

    public static String getName() {
        return name;
    }

    public static void setName(String name) {
        CreateAppointment.name = name;
    }

    public static String getContact() {
        return contact;
    }

    public static void setContact(String contact) {
        CreateAppointment.contact = contact;
    }

    public static String getFather() {
        return father;
    }

    public static void setFather(String father) {
        CreateAppointment.father = father;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Utilities.buttonEffect(btn);
        Utilities.buttonEffect(img);
    }

    @FXML
    public void closeclick(MouseEvent event) throws IOException {
        Window owner = ((Node)event.getTarget()).getScene().getWindow();
        Node node = (Node)event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();
    }
    public void createAppointment(ActionEvent event){
        Window owner = ((Node)event.getTarget()).getScene().getWindow();
        String c;
        try{
            LocalDate local = appointdate.getValue();
            c = local.toString();
        }catch (Exception e){
            c ="n";
        }
        if (c.equals("n")){
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "Please enter Date of Appointment");
            return;
        }
        try{
            LocalTime local = appointtime.getValue();
            c = local.toString();
        }catch (Exception e){
            c ="n";
        }
        if (c.equals("n")){
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "Please enter Time of Appointment");
            return;
        }
        if (appointreason.getText().isEmpty()) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Error!",
                    "Please enter the Reason of Appointment");
            return;
        }
        if (appointdoctor.getText().isEmpty()) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Error!",
                    "Please enter Doctor's Name");
            return;
        }
        String a = appointreason.getText().toString();
        LocalDate localdate = appointdate.getValue();
        LocalTime localtime = appointtime.getValue();
        String b = appointdoctor.getText().toString();
        String message = "Hey "+name+",\n"+"Your appointment has been scheduled on "+localdate.getDayOfWeek()+", "+localdate.getMonth().toString()+" "+localdate.getDayOfMonth()+" "+localdate.getYear()+" at "+localtime.toString()+" Hrs with "+b+" at Ansh Neuro Hospital, Varanasi.\nFor any queries contact on 7355972739\nThank you";
//        sendMessage(contact,message);
        message = "\nHey "+name+",\n"+"Just a reminder\nYour appointment has been scheduled on "+localdate.getDayOfWeek()+", "+localdate.getMonth().toString()+" "+localdate.getDayOfMonth()+" "+localdate.getYear()+" at "+localtime.toString()+" Hrs with "+b+" at Ansh Neuro Hospital, Varanasi.\nFor any queries contact on 7355972739\nThank you";
        submit(a,localdate.toString(),localtime.toString(),b, message);

//        System.out.println(message);
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Appointment Scheduled");
        alert.showAndWait();
        Node node = (Node)event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();
    }
    public static void sendMessage(String mobile, String msg) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message message = Message.creator(new com.twilio.type.PhoneNumber("+91"+mobile), new com.twilio.type.PhoneNumber("+18577633571"),
                msg).create();
        System.out.println(message.getSid());
    }
    public void submit(String a, String b, String c, String d, String e){
        Connection con = null;
        Statement stmt = null;
        Statement stmt2 = null;
        try {
            con = Utilities.getConnection();
            stmt = con.createStatement();
            String sql = "INSERT INTO appointments " + "VALUES(0,'"+ID+"','"+name+"','"+father+"','"+contact+"','"+a+"','"+b+"','"+c+"','"+d+"','"+e+"')";
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
