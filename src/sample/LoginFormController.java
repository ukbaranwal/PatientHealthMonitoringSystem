package sample;

import com.jfoenix.controls.JFXPasswordField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.Window;
import com.jfoenix.controls.JFXTextField;
import java.sql.*;

public class LoginFormController {
    @FXML
    private JFXTextField useridlogin;
    @FXML
    private JFXPasswordField passwordFieldlogin;
    @FXML
    private GridPane gpmain;
    @FXML
    private Button submitloginButton;

    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/userdetails";
    static final String USER = "PHMS";
    static final String PASS = "31101997";

    @FXML
    protected void handleSubmitLoginButtonAction(ActionEvent event) {
        Window owner = submitloginButton.getScene().getWindow();
        if (useridlogin.getText().isEmpty()) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Error!",
                    "Please enter your User ID");
            return;
        }
        if (passwordFieldlogin.getText().isEmpty()) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "Please enter a password");
            return;
        }
        String user = useridlogin.getText().toString();
        String pass = passwordFieldlogin.getText().toString();
        try {
            Connection con = null;
            PreparedStatement psm = null;
            ResultSet rs = null;
            Class.forName(JDBC_DRIVER);
            con = DriverManager.getConnection(DB_URL, USER, PASS);
            String sql = "SELECT * FROM details WHERE userid = ? and pasword = ?";
            psm = con.prepareStatement(sql);
            psm.setString(1, user);
            psm.setString(2, pass);
            rs = psm.executeQuery();
            if (rs.next()) {
                System.out.println("Succes");
//                Stage stage = (Stage) gpmain.getScene().getWindow();
////                Node node = (Node)event.getSource();
////                Stage dialogStage = (Stage) node.getScene().getWindow();
////                dialogStage.close();
//                Scene scene = new Scene(FXMLLoader.load(getClass().getResource("registration_form.fxml")),800,500);
//////                Parent root = event.getSource();
//                stage.setScene(scene);
//                stage.show();
            } else {
                System.out.println("Failed");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    protected void launchRegistrationForm(ActionEvent event) {
        try {
//            Stage stage = (Stage) gpmain.getScene().getWindow();
//            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("registration_form.fxml")), 800, 500);
//            stage.setScene(scene);
//            stage.show();
            Node node = (Node)event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            stage.close();
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("registration_form.fxml")));
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

