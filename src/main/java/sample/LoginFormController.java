package main.java.sample;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXPasswordField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
/*
    Created by: Utkarsh Baranwal
 */
public class LoginFormController implements Initializable {
    @FXML
    private JFXTextField useridlogin;
    @FXML
    private JFXPasswordField passwordFieldlogin;
    @FXML
    private Button submitloginButton, registerbutton;
    @FXML
    private Circle min, close;
    @FXML
    private JFXCheckBox btn_save;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Utilities.buttonEffect(submitloginButton);
        Utilities.buttonEffect(min);
        Utilities.buttonEffect(close);
        Utilities.cursorEffect(registerbutton);
    }
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
                AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Error!",
                        "Please enter correct password");
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
            stage=new Stage();
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("registration_form.fxml")));
            stage.initStyle(StageStyle.TRANSPARENT);
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
//    @FXML
//    protected void savePassword(ActionEvent event){
//        if(btn_save.isSelected()){
////            btn_save.setText("Password Saved");
//            try{
//                Connection con = null;
//                Statement stmt = null;
//                con = Utilities.getConnection();
//                stmt = con.createStatement();
//                String sql = "SELECT * from usercredentials";
//                ResultSet rs = stmt.executeQuery(sql);
//                rs.next();
//
//            }catch (Exception e){
//
//            }
//        }
//    }
}
/*
    Created by: Utkarsh Baranwal
 */
