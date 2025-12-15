package healthcare;

import javax.swing.*;

import healthcare.controller.AppointmentController;
import healthcare.controller.PatientController;
import healthcare.controller.PrescriptionController;
import healthcare.controller.ReferralController;
import healthcare.view.MainView;

public class MainApp {
    public static void main(String[] args) {
        PatientController pc = new PatientController();
        pc.loadPatients();

        AppointmentController ac = new AppointmentController();
        ac.loadAppointments();

        PrescriptionController presc = new PrescriptionController();
        presc.loadPrescriptions();

        ReferralController rc = new ReferralController();
        rc.loadReferrals();

        SwingUtilities.invokeLater(() -> new MainView(ac, presc, rc, pc));
    }
}