package healthcare.model;
import java.time.LocalDate;

public class Referral {
    private int refID;
    private int patientID;
    private int gpID;
    private int specialistID;
    private String reason;
    private LocalDate date;

    public Referral(int refID, int patientID, int gpID, int specialistID, String reason, LocalDate date) {
        this.refID = refID;
        this.patientID = patientID;
        this.gpID = gpID;
        this.specialistID = specialistID;
        this.reason = reason;
        this.date = date;
    }

    public int getRefID() { return refID; }
    public int getPatientID() { return patientID; }
    public int getGpID() { return gpID; }
    public int getSpecialistID() { return specialistID; }
    public String getReason() { return reason; }
    public LocalDate getDate() { return date; }
}