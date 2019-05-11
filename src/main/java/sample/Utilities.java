package main.java.sample;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import java.sql.Connection;
import java.sql.DriverManager;
/*
    Created by: Utkarsh Baranwal
 */
public class Utilities {
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/userdetails";
    static final String USER = "PHMS";
    static final String PASS = "31101997";
    public static String HOSPITAL = "Ansh Neuro and Maternity Centre";
    public static Event event;
    public static Connection getConnection(){
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Class.forName(JDBC_DRIVER);
        } catch (Exception e) {

        }
        return conn;
    }
    public static void buttonEffect(Button btn){
        DropShadow shadow = new DropShadow();
        btn.addEventHandler(MouseEvent.MOUSE_ENTERED,
                new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent e) {
                        btn.setEffect(shadow);
                        Scene scene = ((Node)e.getTarget()).getScene();
                        scene.setCursor(Cursor.HAND);
                    }
                });

        btn.addEventHandler(MouseEvent.MOUSE_EXITED,
                new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent e) {
                        btn.setEffect(null);
                        Scene scene = ((Node)e.getTarget()).getScene();
                        scene.setCursor(Cursor.DEFAULT);
                    }
                });
    }
    public static void buttonEffect(ImageView btn){
        DropShadow shadow = new DropShadow();
        btn.addEventHandler(MouseEvent.MOUSE_ENTERED,
                new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent e) {
                        btn.setEffect(shadow);
                        Scene scene = ((Node)e.getTarget()).getScene();
                        scene.setCursor(Cursor.HAND);
                    }
                });

        btn.addEventHandler(MouseEvent.MOUSE_EXITED,
                new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent e) {
                        btn.setEffect(null);
                        Scene scene = ((Node)e.getTarget()).getScene();
                        scene.setCursor(Cursor.DEFAULT);
                    }
                });
    }
    public static void buttonEffect(Circle btn){
        DropShadow shadow = new DropShadow();
        btn.addEventHandler(MouseEvent.MOUSE_ENTERED,
                new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent e) {
                        btn.setEffect(shadow);
                        Scene scene = ((Node)e.getTarget()).getScene();
                        scene.setCursor(Cursor.HAND);
                    }
                });

        btn.addEventHandler(MouseEvent.MOUSE_EXITED,
                new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent e) {
                        btn.setEffect(null);
                        Scene scene = ((Node)e.getTarget()).getScene();
                        scene.setCursor(Cursor.DEFAULT);
                    }
                });
    }
    public static void cursorEffect(ImageView btn){
        DropShadow shadow = new DropShadow();
        btn.addEventHandler(MouseEvent.MOUSE_ENTERED,
                new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent e) {
                        Scene scene = ((Node)e.getTarget()).getScene();
                        scene.setCursor(Cursor.HAND);
                    }
                });

        btn.addEventHandler(MouseEvent.MOUSE_EXITED,
                new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent e) {
                        Scene scene = ((Node)e.getTarget()).getScene();
                        scene.setCursor(Cursor.DEFAULT);
                    }
                });
    }
    public static void cursorEffect(Button btn){
        DropShadow shadow = new DropShadow();
        btn.addEventHandler(MouseEvent.MOUSE_ENTERED,
                new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent e) {
                        Scene scene = ((Node)e.getTarget()).getScene();
                        scene.setCursor(Cursor.HAND);
                    }
                });

        btn.addEventHandler(MouseEvent.MOUSE_EXITED,
                new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent e) {
                        Scene scene = ((Node)e.getTarget()).getScene();
                        scene.setCursor(Cursor.DEFAULT);
                    }
                });
    }
}
/*
    Created by: Utkarsh Baranwal
 */
