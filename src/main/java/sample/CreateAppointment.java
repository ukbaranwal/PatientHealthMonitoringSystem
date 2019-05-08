package main.java.sample;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTimePicker;
import com.twilio.Twilio;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.Window;
import com.twilio.rest.api.v2010.account.Message;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalTime;

public class CreateAppointment {
    @FXML
    private JFXDatePicker appointdate;
    @FXML
    private JFXTimePicker appointtime;
    @FXML
    private JFXTextArea appointreason;
    @FXML
    private JFXTextField appointdoctor;
    public static int ID;
    public static String name, contact, father;
    public static final String ACCOUNT_SID = "AC304fa123df9a164738a7ee9799e667d3";
    public static final String AUTH_TOKEN = "c937ae9bb30217f676604bc79fc04d23";
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/userdetails";
    static final String USER = "PHMS";
    static final String PASS = "31101997";
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

    @FXML
    public void closeclick(MouseEvent event) throws IOException {
        Window owner = ((Node)event.getTarget()).getScene().getWindow();
        Node node = (Node)event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();
    }
    public void createAppointment(ActionEvent event){
        String a = appointreason.getText().toString();
        LocalDate localdate = appointdate.getValue();
        LocalTime localtime = appointtime.getValue();
        String b = appointdoctor.getText().toString();
        String message = "Hey "+name+",\n"+"Your appointment has been scheduled on "+localdate.getDayOfWeek()+", "+localdate.getMonth().toString()+" "+localdate.getDayOfMonth()+" "+localdate.getYear()+" at "+localtime.toString()+" Hrs with "+b+" at Ansh Neuro Hospital, Varanasi.\nFor any queries contact on 7355972739\nThank you";
        submit(a,localdate.getDayOfMonth()+" "+localdate.getMonth().toString(),localtime.toString(),b, message);
        sendMessage(contact,message);
//        System.out.println(message);
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Appointment Scheduled");
        alert.showAndWait();
        Node node = (Node)event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();
    }
    public static void sendMessage(String mobile, String msg){
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
            Class.forName(JDBC_DRIVER);
            con = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = con.createStatement();
            String sql = "INSERT INTO appointments " + "VALUES(0,'"+ID+"','"+name+"','"+father+"','"+contact+"','"+a+"','"+b+"','"+c+"','"+d+"','"+2+"','"+e+"')";
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
