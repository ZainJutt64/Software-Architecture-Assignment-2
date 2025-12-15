package healthcare.model;

public abstract class Clinician extends User {
    private String specialty;

    public Clinician(int userID, String name, String email, String password, String specialty) {
        super(userID, name, email, password);
        this.specialty = specialty;
    }

    public String getSpecialty() { return specialty; }
    public abstract void performConsultation();
    public abstract void createPrescription();
    public abstract void makeReferral();
}