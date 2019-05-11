package main.java.sample;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
/*
    Created by: Utkarsh Baranwal
 */
public class PatientDetails {

    private SimpleIntegerProperty Height, Weight, ID;
    private SimpleStringProperty Name, Father, Mobile, Email, Gender, Blood, DOB, Status, Emergency, Address, Allergy, CurMedi, PastMedi, Chronicllness, Injuries, Surgeries, Cigarette, Alcohol, Activity, Food, Profession;

    public PatientDetails(String Name, String Father, String Mobile, String Email, String Gender, String DOB, String Blood, String Status, Integer Height, Integer Weight, String Emergency, String Address, String Allergy, String CurMedi, String PastMedi, String ChronicIllness, String Injuries, String Surgeries, String Cigarette, String Alcohol, String Activity, String Food, String Profession, Integer ID) {
        this.ID = new SimpleIntegerProperty(ID);
        this.Height = new SimpleIntegerProperty(Height);
        this.Weight = new SimpleIntegerProperty(Weight);
        this.Name = new SimpleStringProperty(Name);
        this.Father = new SimpleStringProperty(Father);
        this.Mobile = new SimpleStringProperty(Mobile);
        this.Email = new SimpleStringProperty(Email);
        this.Gender = new SimpleStringProperty(Gender);
        this.DOB = new SimpleStringProperty(DOB);
        this.Blood = new SimpleStringProperty(Blood);
        this.Status = new SimpleStringProperty(Status);
        this.Emergency = new SimpleStringProperty(Emergency);
        this.Address = new SimpleStringProperty(Address);
        this.Allergy = new SimpleStringProperty(Allergy);
        this.CurMedi = new SimpleStringProperty(CurMedi);
        this.PastMedi = new SimpleStringProperty(PastMedi);
        this.Chronicllness = new SimpleStringProperty(ChronicIllness);
        this.Injuries = new SimpleStringProperty(Injuries);
        this.Surgeries = new SimpleStringProperty(Surgeries);
        this.Cigarette = new SimpleStringProperty(Cigarette);
        this.Alcohol = new SimpleStringProperty(Alcohol);
        this.Activity = new SimpleStringProperty(Activity);
        this.Food = new SimpleStringProperty(Food);
        this.Activity = new SimpleStringProperty(Activity);
        this.Profession = new SimpleStringProperty(Profession);
    }
    public PatientDetails(String Name, String Father, String Mobile, String Email, String Gender, String DOB, String Emergency, String Address, Integer ID) {
        this.ID = new SimpleIntegerProperty(ID);
        this.Name = new SimpleStringProperty(Name);
        this.Father = new SimpleStringProperty(Father);
        this.Mobile = new SimpleStringProperty(Mobile);
        this.Email = new SimpleStringProperty(Email);
        this.Gender = new SimpleStringProperty(Gender);
        this.DOB = new SimpleStringProperty(DOB);
        this.Emergency = new SimpleStringProperty(Emergency);
        this.Address = new SimpleStringProperty(Address);
    }
    public int getHeight() {
        return Height.get();
    }

    public SimpleIntegerProperty heightProperty() {
        return Height;
    }

    public void setHeight(int height) {
        this.Height.set(height);
    }

    public int getWeight() {
        return Weight.get();
    }

    public SimpleIntegerProperty weightProperty() {
        return Weight;
    }

    public void setWeight(int weight) {
        this.Weight.set(weight);
    }

    public int getID() {
        return ID.get();
    }

    public SimpleIntegerProperty IDProperty() {
        return ID;
    }

    public void setID(int ID) {
        this.ID.set(ID);
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

    public String getEmail() {
        return Email.get();
    }

    public SimpleStringProperty emailProperty() {
        return Email;
    }

    public void setEmail(String email) {
        this.Email.set(email);
    }

    public String getGender() {
        return Gender.get();
    }

    public SimpleStringProperty genderProperty() {
        return Gender;
    }

    public void setGender(String gender) {
        this.Gender.set(gender);
    }

    public String getBlood() {
        return Blood.get();
    }

    public SimpleStringProperty bloodProperty() {
        return Blood;
    }

    public void setBlood(String blood) {
        this.Blood.set(blood);
    }

    public String getDOB() {
        return DOB.get();
    }

    public SimpleStringProperty DOBProperty() {
        return DOB;
    }

    public void setDOB(String DOB) {
        this.DOB.set(DOB);
    }

    public String getStatus() {
        return Status.get();
    }

    public SimpleStringProperty statusProperty() {
        return Status;
    }

    public void setStatus(String status) {
        this.Status.set(status);
    }

    public String getEmergency() {
        return Emergency.get();
    }

    public SimpleStringProperty emergencyProperty() {
        return Emergency;
    }

    public void setEmergency(String emergency) {
        this.Emergency.set(emergency);
    }

    public String getAddress() {
        return Address.get();
    }

    public SimpleStringProperty addressProperty() {
        return Address;
    }

    public void setAddress(String address) {
        this.Address.set(address);
    }

    public String getAllergy() {
        return Allergy.get();
    }

    public SimpleStringProperty allergyProperty() {
        return Allergy;
    }

    public void setAllergy(String allergy) {
        this.Allergy.set(allergy);
    }

    public String getCurMedi() {
        return CurMedi.get();
    }

    public SimpleStringProperty curMediProperty() {
        return CurMedi;
    }

    public void setCurMedi(String curMedi) {
        this.CurMedi.set(curMedi);
    }

    public String getPastMedi() {
        return PastMedi.get();
    }

    public SimpleStringProperty pastMediProperty() {
        return PastMedi;
    }

    public void setPastMedi(String pastMedi) {
        this.PastMedi.set(pastMedi);
    }

    public String getChronicllness() {
        return Chronicllness.get();
    }

    public SimpleStringProperty chronicllnessProperty() {
        return Chronicllness;
    }

    public void setChronicllness(String chronicllness) {
        this.Chronicllness.set(chronicllness);
    }

    public String getInjuries() {
        return Injuries.get();
    }

    public SimpleStringProperty injuriesProperty() {
        return Injuries;
    }

    public void setInjuries(String injuries) {
        this.Injuries.set(injuries);
    }

    public String getSurgeries() {
        return Surgeries.get();
    }

    public SimpleStringProperty surgeriesProperty() {
        return Surgeries;
    }

    public void setSurgeries(String surgeries) {
        this.Surgeries.set(surgeries);
    }

    public String getCigarette() {
        return Cigarette.get();
    }

    public SimpleStringProperty cigaretteProperty() {
        return Cigarette;
    }

    public void setCigarette(String cigarette) {
        this.Cigarette.set(cigarette);
    }

    public String getAlcohol() {
        return Alcohol.get();
    }

    public SimpleStringProperty alcoholProperty() {
        return Alcohol;
    }

    public void setAlcohol(String alcohol) {
        this.Alcohol.set(alcohol);
    }

    public String getActivity() {
        return Activity.get();
    }

    public SimpleStringProperty activityProperty() {
        return Activity;
    }

    public void setActivity(String activity) {
        this.Activity.set(activity);
    }

    public String getFood() {
        return Food.get();
    }

    public SimpleStringProperty foodProperty() {
        return Food;
    }

    public void setFood(String food) {
        this.Food.set(food);
    }

    public String getProfession() {
        return Profession.get();
    }

    public SimpleStringProperty professionProperty() {
        return Profession;
    }

    public void setProfession(String profession) {
        this.Profession.set(profession);
    }
}
/*
    Created by: Utkarsh Baranwal
 */