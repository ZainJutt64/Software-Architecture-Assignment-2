package healthcare.view;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import healthcare.controller.AppointmentController;
import healthcare.controller.PatientController;
import healthcare.controller.PrescriptionController;
import healthcare.controller.ReferralController;
import healthcare.model.Appointment;
import healthcare.model.Patient;
import healthcare.model.Prescription;
import healthcare.model.Referral;

import java.awt.*;
import java.time.LocalDate;
import java.time.LocalTime;

public class MainView extends JFrame {
    private JTabbedPane tabbedPane;
    private JTable appointmentTable, prescriptionTable, referralTable, patientTable;
    private DefaultTableModel aptModel, prescModel, refModel, patModel;

    private AppointmentController appointmentController;
    private PrescriptionController prescriptionController;
    private ReferralController referralController;
    private PatientController patientController;

    public MainView(AppointmentController ac, PrescriptionController pc, ReferralController rc, PatientController patc) {
        this.appointmentController = ac;
        this.prescriptionController = pc;
        this.referralController = rc;
        this.patientController = patc;

        setTitle("Healthcare Management System - MVC Architecture");
        setSize(1100, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Appointments", createAppointmentPanel());
        tabbedPane.addTab("Prescriptions", createPrescriptionPanel());
        tabbedPane.addTab("Referrals", createReferralPanel());
        tabbedPane.addTab("Patients", createPatientPanel());

        add(tabbedPane);
        setVisible(true);
    }

    private JPanel createAppointmentPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        aptModel = new DefaultTableModel(new String[]{"ID", "Date", "Time", "Patient ID", "Doctor ID"}, 0);
        appointmentTable = new JTable(aptModel);
        panel.add(new JScrollPane(appointmentTable), BorderLayout.CENTER);

        JPanel btnPanel = new JPanel();
        JButton refreshBtn = new JButton("Refresh");
        JButton addBtn = new JButton("Add Appointment");

        refreshBtn.addActionListener(e -> loadAppointments());
        addBtn.addActionListener(e -> showAddAppointmentDialog());

        btnPanel.add(refreshBtn);
        btnPanel.add(addBtn);
        panel.add(btnPanel, BorderLayout.SOUTH);

        loadAppointments();
        return panel;
    }

    private JPanel createPrescriptionPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        prescModel = new DefaultTableModel(new String[]{"ID", "Patient ID", "Condition", "Drug", "Date"}, 0);
        prescriptionTable = new JTable(prescModel);
        panel.add(new JScrollPane(prescriptionTable), BorderLayout.CENTER);

        JPanel btnPanel = new JPanel();
        JButton refreshBtn = new JButton("Refresh");
        JButton addBtn = new JButton("Issue Prescription");

        refreshBtn.addActionListener(e -> loadPrescriptions());
        addBtn.addActionListener(e -> showAddPrescriptionDialog());

        btnPanel.add(refreshBtn);
        btnPanel.add(addBtn);
        panel.add(btnPanel, BorderLayout.SOUTH);

        loadPrescriptions();
        return panel;
    }

    private JPanel createReferralPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        refModel = new DefaultTableModel(new String[]{"ID", "Patient ID", "GP ID", "Specialist ID", "Reason", "Date"}, 0);
        referralTable = new JTable(refModel);
        panel.add(new JScrollPane(referralTable), BorderLayout.CENTER);

        JPanel btnPanel = new JPanel();
        JButton refreshBtn = new JButton("Refresh");
        JButton addBtn = new JButton("Make Referral");

        refreshBtn.addActionListener(e -> loadReferrals());
        addBtn.addActionListener(e -> showAddReferralDialog());

        btnPanel.add(refreshBtn);
        btnPanel.add(addBtn);
        panel.add(btnPanel, BorderLayout.SOUTH);

        loadReferrals();
        return panel;
    }

    private JPanel createPatientPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        patModel = new DefaultTableModel(new String[]{"ID", "DOB", "Medical History"}, 0);
        patientTable = new JTable(patModel);
        panel.add(new JScrollPane(patientTable), BorderLayout.CENTER);

        JPanel btnPanel = new JPanel();
        JButton refreshBtn = new JButton("Refresh");
        JButton addBtn = new JButton("Register Patient");

        refreshBtn.addActionListener(e -> loadPatients());
        addBtn.addActionListener(e -> showAddPatientDialog());

        btnPanel.add(refreshBtn);
        btnPanel.add(addBtn);
        panel.add(btnPanel, BorderLayout.SOUTH);

        loadPatients();
        return panel;
    }

    // REAL DATA LOADERS — SHOWS DATA FROM CONTROLLERS AND CSV
    private void loadAppointments() {
        aptModel.setRowCount(0);
        for (Appointment a : appointmentController.getAppointments()) {  // Add getAppointments() in AppointmentController
            aptModel.addRow(new Object[]{
                a.getApptID(),
                a.getDate(),
                a.getTime(),
                a.getPatientID(),
                a.getDoctorID()
            });
        }
    }

    private void loadPrescriptions() {
        prescModel.setRowCount(0);
        for (Prescription p : prescriptionController.getPrescriptions()) {
            prescModel.addRow(new Object[]{
                p.getPresID(),
                p.getPatientID(),
                p.getCondition(),
                p.getDrug(),
                p.getDateIssued()
            });
        }
    }

    private void loadReferrals() {
        refModel.setRowCount(0);
        for (Referral r : referralController.getReferrals()) {
            refModel.addRow(new Object[]{
                r.getRefID(),
                r.getPatientID(),
                r.getGpID(),
                r.getSpecialistID(),
                r.getReason(),
                r.getDate()
            });
        }
    }

    private void loadPatients() {
        patModel.setRowCount(0);
        for (Patient p : patientController.getPatients()) {
            patModel.addRow(new Object[]{
                p.getPatientID(),
                p.getDob(),
                p.getMedicalHistory()
            });
        }
    }

    // DIALOGS (already safe with try-catch)
    private void showAddAppointmentDialog() {
        JTextField patId = new JTextField(5);
        JTextField docId = new JTextField(5);
        JPanel p = new JPanel();
        p.add(new JLabel("Patient ID:")); p.add(patId);
        p.add(new JLabel("Doctor ID:")); p.add(docId);

        if (JOptionPane.showConfirmDialog(this, p, "New Appointment", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
            try {
                int pId = Integer.parseInt(patId.getText());
                int dId = Integer.parseInt(docId.getText());
                appointmentController.bookAppointment(pId, dId, LocalDate.now(), LocalTime.now());
                loadAppointments();
                JOptionPane.showMessageDialog(this, "Appointment Booked!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Invalid input! Use numbers for IDs.");
            }
        }
    }

    private void showAddPrescriptionDialog() {
        JTextField patId = new JTextField(5);
        JTextField condition = new JTextField(15);
        JTextField drug = new JTextField(15);
        JPanel p = new JPanel();
        p.add(new JLabel("Patient ID:")); p.add(patId);
        p.add(new JLabel("Condition:")); p.add(condition);
        p.add(new JLabel("Drug:")); p.add(drug);

        if (JOptionPane.showConfirmDialog(this, p, "New Prescription", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
            try {
                int pId = Integer.parseInt(patId.getText());
                prescriptionController.createPrescription(pId, condition.getText(), drug.getText(), LocalDate.now());
                loadPrescriptions();
                JOptionPane.showMessageDialog(this, "Prescription Issued!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Patient ID must be a number!");
            }
        }
    }

    private void showAddReferralDialog() {
        JTextField patId = new JTextField(5);
        JTextField gpId = new JTextField(5);
        JTextField specId = new JTextField(5);
        JTextField reason = new JTextField(20);
        JPanel p = new JPanel();
        p.add(new JLabel("Patient ID:")); p.add(patId);
        p.add(new JLabel("GP ID:")); p.add(gpId);
        p.add(new JLabel("Specialist ID:")); p.add(specId);
        p.add(new JLabel("Reason:")); p.add(reason);

        if (JOptionPane.showConfirmDialog(this, p, "Make Referral", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
            try {
                int pId = Integer.parseInt(patId.getText());
                int gId = Integer.parseInt(gpId.getText());
                int sId = Integer.parseInt(specId.getText());
                referralController.makeReferral(pId, gId, sId, reason.getText(), LocalDate.now(), "Referral note");
                patientController.updateEHR(pId, "Referred: " + reason.getText());
                loadReferrals();
                JOptionPane.showMessageDialog(this, "Referral Made!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "IDs must be numbers!");
            }
        }
    }

    private void showAddPatientDialog() {
        JTextField dob = new JTextField("yyyy-mm-dd", 10);
        JPanel p = new JPanel();
        p.add(new JLabel("DOB (yyyy-mm-dd):")); p.add(dob);

        if (JOptionPane.showConfirmDialog(this, p, "Register Patient", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
            try {
                LocalDate date = LocalDate.parse(dob.getText());
                patientController.registerPatient(date, "New patient registered");
                loadPatients();
                JOptionPane.showMessageDialog(this, "Patient Registered!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Invalid date format! Use yyyy-mm-dd");
            }
        }
    }
}