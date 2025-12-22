package healthcare.controller;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import healthcare.model.Patient;
import healthcare.util.CSVUtil;

public class PatientController {
    private List<Patient> patients = new ArrayList<>();
    private int nextID = 1;

    public void loadPatients() {
        List<String[]> data = CSVUtil.readCSV("patients.csv");
        if (data.isEmpty()) return;
        for (String[] row : data.subList(1, data.size())) {
            try {
                int id = Integer.parseInt(row[0]);
                LocalDate dob = LocalDate.parse(row[2]);
                String history = row.length > 8 ? row[8] : "";
                Patient p = new Patient(id, dob, history);
                patients.add(p);
                nextID = Math.max(nextID, id + 1);
            } catch (Exception e) {}
        }
    }

    public void registerPatient(LocalDate dob, String medicalHistory) {
        Patient p = new Patient(nextID++, dob, medicalHistory);
        patients.add(p);
        savePatients();
    }

    public void updateEHR(int patientID, String update) {
        for (Patient p : patients) {
            if (p.getPatientID() == patientID) {
                p.setMedicalHistory(p.getMedicalHistory() + "\n" + update);
                savePatients();
                return;
            }
        }
    }

    private void savePatients() {
        List<String[]> data = new ArrayList<>();
        data.add(new String[]{"PatientID","Name","DOB","Address","Phone","Email","NHSNumber","GPSurgeryID","MedicalHistory"});
        for (Patient p : patients) {
            data.add(new String[]{
                String.valueOf(p.getPatientID()), "", p.getDob().toString(), "", "", "", "", "", p.getMedicalHistory()
            });
        }
        CSVUtil.writeCSV("patients.csv", data, false);
    }

    public List<Patient> getPatients() {
        return patients;
    }
}