package main.java.sample;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.stage.Window;
/*
    Created by: Utkarsh Baranwal
 */
public class RegistrationFormController implements Initializable {
    @FXML
    private TextField nameField, address, doctor, userid;
    @FXML
    private PasswordField passwordField, confirmField;
    @FXML
    private Circle min, close;
    @FXML
    private Button submitButton, loginbutton;
    public void register(String a, String b, String c, String d, String e) {
        Connection con = null;
        Statement stmt = null;
        try {
            con = Utilities.getConnection();
            stmt = con.createStatement();
            String sql = "INSERT INTO details " + "VALUES (0,'"+a+"','"+b+"','"+c+"','"+d+"','"+e+"')";
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Utilities.buttonEffect(submitButton);
        Utilities.buttonEffect(min);
        Utilities.buttonEffect(close);
        Utilities.cursorEffect(loginbutton);
    }

    @FXML
    protected void handleSubmitButtonAction (ActionEvent event){
        Window owner = submitButton.getScene().getWindow();
        if (nameField.getText().isEmpty()) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "Please enter Hospital's name");
            return;
        }
        if (address.getText().isEmpty()) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "Please enter Hospital's address");
            return;
        }
        if (doctor.getText().isEmpty()) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "Please enter Doctor's name");
            return;
        }
        if (userid.getText().isEmpty()) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "Please choose a valid User id");
            return;
        }
        if (passwordField.getText().isEmpty()) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "Please enter a password");
            return;
        }
        if (confirmField.getText().isEmpty()) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "Please confirm your password");
            return;
        }
        if (!passwordField.getText().equals(confirmField.getText())){
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "Password doesn't match");
            return;
        }
        String a = nameField.getText().toString();
        String b = address.getText().toString();
        String c = doctor.getText().toString();
        String d = userid.getText().toString();
        String e = passwordField.getText().toString();
        register(a,b,c,d,e);
        AlertHelper.showAlert(Alert.AlertType.CONFIRMATION, owner, "Registration Successful!",
                "Welcome ");
    }
    @FXML
    protected void launchLoginForm(ActionEvent event) {
        try {
            Node node = (Node)event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            stage.close();
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("login_form.fxml")));
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void minclick(MouseEvent event) throws IOException {
        ((Stage)((Circle)event.getSource()).getScene().getWindow()).setIconified(true);
    }
    @FXML
    public void closeclick(MouseEvent event) throws IOException {
        System.exit(0);
    }
}
/*
    Created by: Utkarsh Baranwal
 */