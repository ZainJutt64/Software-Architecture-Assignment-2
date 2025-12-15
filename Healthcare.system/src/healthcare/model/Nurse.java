package healthcare.model;

public class Nurse extends User {
    public Nurse(int userID, String name, String email, String password) {
        super(userID, name, email, password);
    }

    public void managePatientRecords() { System.out.println("Nurse " + getName() + " managing records"); }
    public void updateEHR() { System.out.println("Nurse " + getName() + " updating EHR"); }
}