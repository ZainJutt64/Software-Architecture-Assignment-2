package healthcare.model;
import java.time.LocalDate;

public class Patient {
    private int patientID;
    private LocalDate dob;
    private String medicalHistory;

    public Patient(int patientID, LocalDate dob, String medicalHistory) {
        this.patientID = patientID;
        this.dob = dob;
        this.medicalHistory = medicalHistory == null ? "" : medicalHistory;
    }

    public int getPatientID() { return patientID; }
    public LocalDate getDob() { return dob; }
    public String getMedicalHistory() { return medicalHistory; }
    public void setMedicalHistory(String history) { this.medicalHistory = history; }
}