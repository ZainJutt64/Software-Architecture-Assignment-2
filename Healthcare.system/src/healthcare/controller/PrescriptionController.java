package healthcare.controller;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import healthcare.model.Prescription;
import healthcare.util.CSVUtil;

public class PrescriptionController {
    private List<Prescription> prescriptions = new ArrayList<>();
    private int nextID = 1;

    public void loadPrescriptions() {
        List<String[]> data = CSVUtil.readCSV("prescriptions.csv");
        if (data.isEmpty()) return;
        for (String[] row : data.subList(1, data.size())) {
            try {
                Prescription p = new Prescription(
                    Integer.parseInt(row[0]),
                    Integer.parseInt(row[1]),
                    row[2],
                    row[3],
                    LocalDate.parse(row[4])
                );
                prescriptions.add(p);
                nextID = Math.max(nextID, p.getPresID() + 1);
            } catch (Exception e) {}
        }
    }

    public void createPrescription(int patientID, String condition, String drug, LocalDate dateIssued) {
        Prescription p = new Prescription(nextID++, patientID, condition, drug, dateIssued);
        prescriptions.add(p);
        savePrescriptions();
    }

    private void savePrescriptions() {
        List<String[]> data = new ArrayList<>();
        data.add(new String[]{"PrescriptionID","PatientID","Condition","Drug","DateIssued"});
        for (Prescription p : prescriptions) {
            data.add(new String[]{
                String.valueOf(p.getPresID()),
                String.valueOf(p.getPatientID()),
                p.getCondition(),
                p.getDrug(),
                p.getDateIssued().toString()
            });
        }
        CSVUtil.writeCSV("prescriptions.csv", data, false);
    }

    public List<Prescription> getPrescriptions() {
        return prescriptions;
    }
}