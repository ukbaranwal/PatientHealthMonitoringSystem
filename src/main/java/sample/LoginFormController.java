package main.java.sample;
import com.jfoenix.controls.JFXPasswordField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.sql.*;

public class LoginFormController {
    @FXML
    private JFXTextField useridlogin;
    @FXML
    private JFXPasswordField passwordFieldlogin;
    @FXML
    private Button submitloginButton;

    @FXML
    protected void handleSubmitLoginButtonAction(ActionEvent event) {
        Window owner = submitloginButton.getScene().getWindow();
        if (useridlogin.getText().isEmpty()) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Error!",
                    "Please enter your User ID");
            return;
        }
        if (passwordFieldlogin.getText().isEmpty()) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Error!",
                    "Please enter a password");
            return;
        }
        String user = useridlogin.getText().toString();
        String pass = passwordFieldlogin.getText().toString();
        try {
            Connection con = null;
            PreparedStatement psm = null;
            ResultSet rs = null;
            con = Utilities.getConnection();
            String sql = "SELECT * FROM details WHERE userid = ? and pasword = ?";
            psm = con.prepareStatement(sql);
            psm.setString(1, user);
            psm.setString(2, pass);
            rs = psm.executeQuery();
            if (rs.next()) {
                Node node = (Node)event.getSource();
                Stage stage = (Stage) node.getScene().getWindow();
                stage.close();
                stage = new Stage();
                Scene scene = new Scene(FXMLLoader.load(getClass().getResource("patient_list.fxml")));
                stage.setScene(scene);
                stage.show();
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
    @FXML
    public void minclick(MouseEvent event) throws IOException {
        ((Stage)((Circle)event.getSource()).getScene().getWindow()).setIconified(true);
    }
    @FXML
    public void closeclick(MouseEvent event) throws IOException {
        System.exit(0);
    }
}

