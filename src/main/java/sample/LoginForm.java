package main.java.sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

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
        String sql = "SELECT hospital from details where id=1";
        ResultSet rs = stmt.executeQuery(sql);
        rs.next();
        Utilities.HOSPITAL=rs.getString("hosptial");
        Parent root = FXMLLoader.load(getClass().getResource("patient_list.fxml"));
//        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
}