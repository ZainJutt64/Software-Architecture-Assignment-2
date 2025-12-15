package healthcare.model;
import java.time.LocalDate;

public class Schedule {
    private int scheduleID;
    private int doctorID;
    private LocalDate date;
    private String timeSlots;

    public Schedule(int scheduleID, int doctorID, LocalDate date, String timeSlots) {
        this.scheduleID = scheduleID;
        this.doctorID = doctorID;
        this.date = date;
        this.timeSlots = timeSlots;
    }

    public int getScheduleID() { return scheduleID; }
    public int getDoctorID() { return doctorID; }
    public LocalDate getDate() { return date; }
    public String getTimeSlots() { return timeSlots; }
}