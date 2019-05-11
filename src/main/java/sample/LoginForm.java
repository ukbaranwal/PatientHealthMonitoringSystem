package main.java.sample;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.sql.Connection;
import java.sql.Statement;
/*
    Created by: Utkarsh Baranwal
 */
public class LoginForm extends Application {
    public static void main(String[] args) {

        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        Connection conn = null;
        Statement stmt = null;
        conn = Utilities.getConnection();
        stmt = conn.createStatement();
        Parent root = FXMLLoader.load(getClass().getResource("login_form.fxml"));
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
}
/*
    Created by: Utkarsh Baranwal
 */