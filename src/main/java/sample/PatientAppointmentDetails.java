package main.java.sample;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class PatientAppointmentDetails {

    private SimpleIntegerProperty ID, NO;
    private SimpleStringProperty Name, Father, Mobile, Reason, Dateappointment, Timeappointment, Doctor;

    public PatientAppointmentDetails(Integer NO, Integer ID, String Name, String Father, String Mobile, String Reason, String Dateappoimtment, String Timeappointment, String Doctor) {
        this.ID = new SimpleIntegerProperty(ID);
        this.NO = new SimpleIntegerProperty(NO);
        this.Name = new SimpleStringProperty(Name);
        this.Father = new SimpleStringProperty(Father);
        this.Mobile = new SimpleStringProperty(Mobile);
        this.Dateappointment = new SimpleStringProperty(Dateappoimtment);
        this.Timeappointment = new SimpleStringProperty(Timeappointment);
        this.Reason = new SimpleStringProperty(Reason);
        this.Doctor = new SimpleStringProperty(Doctor);
    }

    public int getID() {
        return ID.get();
    }

    public SimpleIntegerProperty IDProperty() {
        return ID;
    }

    public String getDateappointment() {
        return Dateappointment.get();
    }

    public SimpleStringProperty dateappointmentProperty() {
        return Dateappointment;
    }

    public void setDateappointment(String dateappointment) {
        this.Dateappointment.set(dateappointment);
    }

    public String getTimeappointment() {
        return Timeappointment.get();
    }

    public SimpleStringProperty timeappointmentProperty() {
        return Timeappointment;
    }

    public void setTimeappointment(String timeappointment) {
        this.Timeappointment.set(timeappointment);
    }

    public void setID(int ID) {
        this.ID.set(ID);
    }

    public int getNO() {
        return NO.get();
    }

    public SimpleIntegerProperty NOProperty() {
        return NO;
    }

    public void setNO(int NO) {
        this.NO.set(NO);
    }

    public String getName() {
        return Name.get();
    }

    public SimpleStringProperty nameProperty() {
        return Name;
    }

    public void setName(String name) {
        this.Name.set(name);
    }

    public String getFather() {
        return Father.get();
    }

    public SimpleStringProperty fatherProperty() {
        return Father;
    }

    public void setFather(String father) {
        this.Father.set(father);
    }

    public String getMobile() {
        return Mobile.get();
    }

    public SimpleStringProperty mobileProperty() {
        return Mobile;
    }

    public void setMobile(String mobile) {
        this.Mobile.set(mobile);
    }

    public String getReason() {
        return Reason.get();
    }

    public SimpleStringProperty reasonProperty() {
        return Reason;
    }

    public void setReason(String reason) {
        this.Reason.set(reason);
    }


    public String getDoctor() {
        return Doctor.get();
    }

    public SimpleStringProperty doctorProperty() {
        return Doctor;
    }

    public void setDoctor(String doctor) {
        this.Doctor.set(doctor);
    }
}
