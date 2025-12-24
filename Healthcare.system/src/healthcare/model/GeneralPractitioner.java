package healthcare.model;
public class GeneralPractitioner extends Clinician {
    public GeneralPractitioner(int userID, String name, String email, String password, String specialty) {
        super(userID, name, email, password, specialty);
    }

    @Override public void performConsultation() { System.out.println("GP " + getName() + " consulting"); }
    @Override public void createPrescription() { System.out.println("GP " + getName() + " prescribing"); }
    @Override public void makeReferral() { System.out.println("GP " + getName() + " making referral"); }
}