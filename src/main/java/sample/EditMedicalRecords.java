package main.java.sample;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.List;

public class EditMedicalRecords implements Initializable {
    @FXML
    private ListView<String> medicalrecordslist;
    @FXML
    private Label medicalrecordlabel;
    @FXML
    private Button btn_record;
    private static int ID;
    private static final String PATH = "/Users/utkarsh/MedicalRecords/";
    public static int getID() {
        return ID;
    }
    public static void setID(int ID) {
        EditMedicalRecords.ID = ID;
    }
    List<File> list = null;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Utilities.buttonEffect(btn_record);
        String[] list;
        File f = null;
        medicalrecordlabel.setText("Patient ID : "+ID);
        javafx.scene.control.MenuItem item1 = new javafx.scene.control.MenuItem("Delete this Record");
        item1.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                try{
                    File f2 = new File(PATH+"patientID_"+ID+"/"+medicalrecordslist.getSelectionModel().getSelectedItem());
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to Logout", ButtonType.CANCEL, ButtonType.YES);
                    alert.showAndWait();
                    if(alert.getResult()==ButtonType.CANCEL){
                        return;
                    }else{
                        f2.delete();
                    }
                    reinitialize();
                }catch (Exception ex){

                }
            }
        });
        final ContextMenu contextMenu = new ContextMenu(item1);
        medicalrecordslist.setContextMenu(contextMenu);
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
                        System.out.println(PATH+"patientID_"+ID+"/"+record);
                        File f = new File(PATH+"patientID_"+ID+"/"+record);
                        Desktop dt = Desktop.getDesktop();
                        dt.open(f);
                    } catch (Exception e) {

                    }
                }
            }
        });
    }
    private void reinitialize(){
        String[] list;
        File f = null;
        ArrayList<String> array = new ArrayList<String>();
        try{
            f=new File("/Users/utkarsh/MedicalRecords/patientID_"+ID);
            list = f.list();
            ObservableList <String> observableList = FXCollections.observableArrayList(list);
            medicalrecordslist.setItems(observableList);
        }catch (Exception e) {

        }
    }
    @FXML
    protected void addMedicalRecords() throws IOException {
        FileChooser fileChooser = new FileChooser();
        //Set extension filter
        FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
        fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);
        String y = "";
        list = fileChooser.showOpenMultipleDialog(null);
        int record = 1;
        try {
            if(!list.isEmpty()) {
                for (File file : list) {
                    File temp = new File(PATH+"patientID_" + ID + "/medicalrecord" + record+".jpg");
                    while(temp.exists()) {
                        record++;
                        temp = new File(PATH+"patientID_" + ID + "/medicalrecord" + record+".jpg");
                    }
                    Path src = Paths.get(file.getAbsolutePath().toString());
                    Path dest = Paths.get(PATH+"patientID_" + ID + "/medicalrecord" + record+".jpg");
                    Files.copy(src, dest);
                    record++;
                }
                reinitialize();
            }
        } catch (Exception e){

        }
    }
}
