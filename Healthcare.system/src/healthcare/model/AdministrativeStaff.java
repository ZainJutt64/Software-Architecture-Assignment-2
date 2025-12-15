package healthcare.model;

public class AdministrativeStaff extends User {
    public AdministrativeStaff(int userID, String name, String email, String password) {
        super(userID, name, email, password);
    }

    public void registerPatient() { System.out.println("Admin " + getName() + " registering patient"); }
    public void bookAppointment() { System.out.println("Admin " + getName() + " booking appointment"); }
}