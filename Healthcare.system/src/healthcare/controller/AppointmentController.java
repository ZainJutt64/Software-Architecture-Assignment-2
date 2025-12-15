package healthcare.controller;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import healthcare.model.Appointment;
import healthcare.util.CSVUtil;

public class AppointmentController {
    private List<Appointment> appointments = new ArrayList<>();
    private int nextID = 1;

    public void loadAppointments() {
        List<String[]> data = CSVUtil.readCSV("appointments.csv");
        if (data.isEmpty()) return;
        for (String[] row : data.subList(1, data.size())) {
            try {
                Appointment a = new Appointment(
                    Integer.parseInt(row[0]),
                    LocalDate.parse(row[1]),
                    LocalTime.parse(row[2]),
                    Integer.parseInt(row[3]),
                    Integer.parseInt(row[4])
                );
                appointments.add(a);
                nextID = Math.max(nextID, a.getApptID() + 1);
            } catch (Exception e) {}
        }
    }

    public void bookAppointment(int patientID, int doctorID, LocalDate date, LocalTime time) {
        Appointment a = new Appointment(nextID++, date, time, patientID, doctorID);
        appointments.add(a);
        saveAppointments();
    }

    private void saveAppointments() {
        List<String[]> data = new ArrayList<>();
        data.add(new String[]{"AppointmentID","Date","Time","PatientID","DoctorID","Status","Reason"});
        for (Appointment a : appointments) {
            data.add(new String[]{
                String.valueOf(a.getApptID()),
                a.getDate().toString(),
                a.getTime().toString(),
                String.valueOf(a.getPatientID()),
                String.valueOf(a.getDoctorID()),
                "", ""
            });
        }
        CSVUtil.writeCSV("appointments.csv", data, false);
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }
}