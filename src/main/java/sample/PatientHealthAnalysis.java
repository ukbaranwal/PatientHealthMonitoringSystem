
package main.java.sample;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.stage.Stage;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class PatientHealthAnalysis{
    public static int ID;

    public static int getID() {
        return ID;
    }
    public static void setID(int ID) {
        PatientHealthAnalysis.ID = ID;
    }
    public static void runAnalysis(){
        Stage primaryStage = new Stage();
        CategoryAxis xAxis = new CategoryAxis();

        xAxis.setLabel("Appointment Dates");
        //Defining the y axis
        NumberAxis yAxis = new NumberAxis   (0, 10, 1);
        yAxis.setLabel("Health");

        //Creating the line chart
        LineChart linechart = new LineChart(xAxis, yAxis);

        linechart.setTitle("Patient Health Analysis");
        linechart.setPrefHeight(450);
        linechart.setPrefWidth(700);
        //Prepare XYChart.Series objects by setting data
        XYChart.Series series = new XYChart.Series();
        series.setName("Health Level");
        try {
            Connection conn = null;
            Statement stmt = null;
            PatientAppointmentDetails pd = null;
            conn = Utilities.getConnection();
            stmt = conn.createStatement();
            String sql = "SELECT appointdate, healthrating from archivedappointments where id='"+ID+"' order by appointdate";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                series.getData().add(new XYChart.Data(rs.getString("appointdate"),rs.getInt("healthrating")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //Setting the data to Line chart
        linechart.getData().add(series);

        //Creating a Group object
        Group root = new Group(linechart);

        //Creating a scene object
        Scene scene = new Scene(root, 700, 450);
        scene.getStylesheets().add("main/java/resources/stylesheet.css");

        //Setting title to the Stage
        primaryStage.setTitle("Line Chart");

        //Adding scene to the stage
        primaryStage.setScene(scene);

        //Displaying the contents of the stage
        primaryStage.show();
    }
}