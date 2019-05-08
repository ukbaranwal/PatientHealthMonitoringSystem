package main.java.sample;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;


import java.io.File;
import java.net.URL;
import java.util.*;

public class ShowMedicalRecords implements Initializable {
    @FXML
    private ListView<String> medicalrecordslist;
    @FXML
    private Label medicalrecordlabel;
    private static int ID;

    public static int getID() {
        return ID;
    }
    public static void setID(int ID) {
        ShowMedicalRecords.ID = ID;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        String[] list;
        File f =null;
        ArrayList<String> array = new ArrayList<String>();
        medicalrecordlabel.setText("Patient ID : "+ID);
        try{
            f=new File("/Users/utkarsh/MedicalRecords/patientID_"+ID);
            list = f.list();
            ObservableList <String> observableList = FXCollections.observableArrayList(list);
            medicalrecordslist.setItems(observableList);
        }catch (Exception e){

        }

    }
}
