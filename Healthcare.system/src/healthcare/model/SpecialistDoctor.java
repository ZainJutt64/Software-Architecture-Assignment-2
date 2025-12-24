package healthcare.model;
public class SpecialistDoctor extends Clinician {
    public SpecialistDoctor(int userID, String name, String email, String password, String specialty) {
        super(userID, name, email, password, specialty);
    }

    @Override public void performConsultation() { System.out.println("Specialist " + getName() + " consulting"); }
    @Override public void createPrescription() { System.out.println("Specialist " + getName() + " prescribing"); }
    @Override public void makeReferral() { System.out.println("Specialist " + getName() + " making referral"); }
    public void viewReferral() { System.out.println("Specialist " + getName() + " viewing referral"); }
}