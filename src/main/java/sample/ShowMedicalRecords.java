package main.java.sample;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import java.awt.*;
import java.io.File;
import java.net.URL;
import java.util.*;
import java.util.List;
/*
    Created by: Utkarsh Baranwal
 */
public class ShowMedicalRecords implements Initializable {
    @FXML
    private ListView<String> medicalrecordslist;
    @FXML
    private Label medicalrecordlabel;
    @FXML
    private Button btn_record;
    private static int ID;
    static final String PATH = "/Users/utkarsh/MedicalRecords/";
    public static int getID() {
        return ID;
    }
    public static void setID(int ID) {
        ShowMedicalRecords.ID = ID;
    }
    List<File> list = null;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        String[] list;
        File f = null;
        medicalrecordlabel.setText("Patient ID : "+ID);
        try{
            f=new File("/Users/utkarsh/MedicalRecords/patientID_"+ID);
            list = f.list();
            ObservableList <String> observableList = FXCollections.observableArrayList(list);
            medicalrecordslist.setItems(observableList);
        }catch (Exception e){

        }medicalrecordslist.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                if (event.getClickCount() == 2) {
                    try {

                        String record = medicalrecordslist.getSelectionModel().getSelectedItem().toString();
                        File f = new File(PATH+"patientID_"+ID+"/"+record);
                        Desktop dt = Desktop.getDesktop();
                        dt.open(f);
                    } catch (Exception e) {

                    }
                }
            }
        });
    }
}
/*
    Created by: Utkarsh Baranwal
 */