package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class LoginForm extends Application {
    //    @Override
//    public void start(Stage primaryStage) throws Exception{
//        Parent root = FXMLLoader.load(getClass().getResource("registration_form.fxml"));
//        primaryStage.setTitle("Patient Health Monitoring System");
//        primaryStage.setScene(new Scene(root, 800, 500));
//        primaryStage.show();
//    }
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("login_form.fxml"));
        primaryStage.setTitle("Patient Health Monitoring System");
        primaryStage.setScene(new Scene(root,800,500));
        primaryStage.show();
    }
}