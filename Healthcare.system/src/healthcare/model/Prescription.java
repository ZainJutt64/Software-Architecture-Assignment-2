package healthcare.model;
import java.time.LocalDate;

public class Prescription {
    private int presID;
    private int patientID;
    private String condition;
    private String drug;
    private LocalDate dateIssued;

    public Prescription(int presID, int patientID, String condition, String drug, LocalDate dateIssued) {
        this.presID = presID;
        this.patientID = patientID;
        this.condition = condition;
        this.drug = drug;
        this.dateIssued = dateIssued;
    }

    public int getPresID() { return presID; }
    public int getPatientID() { return patientID; }
    public String getCondition() { return condition; }
    public String getDrug() { return drug; }
    public LocalDate getDateIssued() { return dateIssued; }
}