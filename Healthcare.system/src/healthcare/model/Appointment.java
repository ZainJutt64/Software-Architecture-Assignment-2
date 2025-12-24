package healthcare.model;
import java.time.*;

public class Appointment {
    private int apptID;
    private LocalDate date;
    private LocalTime time;
    private int patientID;
    private int doctorID;

    public Appointment(int apptID, LocalDate date, LocalTime time, int patientID, int doctorID) {
        this.apptID = apptID;
        this.date = date;
        this.time = time;
        this.patientID = patientID;
        this.doctorID = doctorID;
    }

    public int getApptID() { return apptID; }
    public LocalDate getDate() { return date; }
    public LocalTime getTime() { return time; }
    public int getPatientID() { return patientID; }
    public int getDoctorID() { return doctorID; }
}