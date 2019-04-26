package sample;
import java.sql.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.Window;
public class RegistrationFormController {
    @FXML
    private TextField nameField, address, regdid, doctor, userid;
    @FXML
    private PasswordField passwordField;
    @FXML private GridPane gpreg;
    @FXML
    private Button submitButton;
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/userdetails";
    static final String USER = "PHMS";
    static final String PASS = "31101997";
    public void register(String a, String b, String c, String d, String e, String f) {
        Connection con = null;
        Statement stmt = null;
        try {
            Class.forName(JDBC_DRIVER);
            con = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = con.createStatement();
            String sql = "INSERT INTO details " + "VALUES ('102','"+a+"','"+b+"','"+c+"','"+d+"','"+e+"','"+f+"')";
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
    @FXML
    protected void handleSubmitButtonAction (ActionEvent event){
        Window owner = submitButton.getScene().getWindow();
        if (nameField.getText().isEmpty()) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "Please enter your name");
            return;
        }
        if (address.getText().isEmpty()) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "Please enter your address");
            return;
        }
        if (regdid.getText().isEmpty()) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "Please enter your registration no.");
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
        String a = nameField.getText().toString();
        String b = address.getText().toString();
        String c = regdid.getText().toString();
        String d = doctor.getText().toString();
        String e = userid.getText().toString();
        String f = passwordField.getText().toString();
        register(a,b,c,d,e,f);
        AlertHelper.showAlert(Alert.AlertType.CONFIRMATION, owner, "Registration Successful!",
                "Welcome ");
    }
    @FXML
    protected void launchLoginForm(ActionEvent event) {
        try {
            Stage stage = (Stage) gpreg.getScene().getWindow();
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("login_form.fxml")), 800, 500);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
