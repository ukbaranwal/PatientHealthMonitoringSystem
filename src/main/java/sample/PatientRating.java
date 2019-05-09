package main.java.sample;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTimePicker;
import com.twilio.Twilio;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.Window;
import com.twilio.rest.api.v2010.account.Message;

import java.io.*;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;

public class PatientRating{
    @FXML
    private JFXTextArea remarks;
    @FXML
    private JFXTextField ratings;
    public static int NO, ID;
    public static String name, contact, father, reason, date, time, doctor;
    public static int getID() {
        return ID;
    }

    public static void setID(int ID) {
        PatientRating.ID = ID;
    }

    public static String getName() {
        return name;
    }

    public static void setName(String name) {
        PatientRating.name = name;
    }

    public static String getContact() {
        return contact;
    }

    public static void setContact(String contact) {
        PatientRating.contact = contact;
    }

    public static String getFather() {
        return father;
    }

    public static void setFather(String father) {
        PatientRating.father = father;
    }

    public static int getNO() {
        return NO;
    }

    public static void setNO(int NO) {
        PatientRating.NO = NO;
    }

    public static String getReason() {
        return reason;
    }

    public static void setReason(String reason) {
        PatientRating.reason = reason;
    }

    public static String getDate() {
        return date;
    }

    public static void setDate(String date) {
        PatientRating.date = date;
    }

    public static String getTime() {
        return time;
    }

    public static void setTime(String time) {
        PatientRating.time = time;
    }

    public static String getDoctor() {
        return doctor;
    }

    public static void setDoctor(String doctor) {
        PatientRating.doctor = doctor;
    }
    public void submit(String a, Integer b){
        Connection con = null;
        Statement stmt = null;
        try {
            con = Utilities.getConnection();
            stmt = con.createStatement();
            String sql = "INSERT INTO archivedappointments " + "VALUES('"+NO+"','"+ID+"','"+name+"','"+father+"','"+contact+"','"+reason+"','"+date+"','"+time+"','"+doctor+"','"+a+"','"+b+"')";
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
        con = null;
        stmt = null;
        try {
            con = Utilities.getConnection();
            stmt = con.createStatement();
            String sql = "DELETE from appointments where number="+NO;
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
        con = null;
        stmt = null;
        try {
            con = Utilities.getConnection();
            stmt = con.createStatement();
            String sql = "SELECT ratings from patientdetails where id="+ID;
            ResultSet rs = stmt.executeQuery(sql);
            rs.next();
            String rating = rs.getString("ratings");
            rating = rating + ", " + ratings.getText();
            sql = "UPDATE patientdetails set ratings='"+rating+"' where id='"+ID+"'";
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
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Successfully Moved to archive");
        alert.showAndWait();
    }
    @FXML
    protected void submitratings(ActionEvent event) throws IOException{
        Window owner = ((Node)event.getTarget()).getScene().getWindow();
        if (ratings.getText().isEmpty()) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Error!",
                    "Please Give Health rating of Patient");
            return;
        }
        try {
            if(Integer.parseInt(ratings.getText())>10){
                AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Error!",
                        "Please Give Health rating out of 10");
                return;
            }
        }catch (Exception e){
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Error!",
                    "Ratings Should be Numerical");
            return;
        }
        submit(remarks.getText(),Integer.parseInt(ratings.getText()));
        Node node = (Node)event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();
        stage = new Stage();
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("appointment_patients.fxml")));
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void closeclick(MouseEvent event) throws IOException {
        Window owner = ((Node)event.getTarget()).getScene().getWindow();
        Node node = (Node)event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();
    }
}
