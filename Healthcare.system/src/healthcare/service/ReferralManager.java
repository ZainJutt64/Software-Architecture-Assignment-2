package healthcare.service;
import java.io.*;
import java.util.*;

import healthcare.model.Referral;
import healthcare.util.CSVUtil;

public class ReferralManager {
    private static ReferralManager instance;
    private List<Referral> queue = new ArrayList<>();

    private ReferralManager() {}

    public static ReferralManager getInstance() {
        if (instance == null) instance = new ReferralManager();
        return instance;
    }

    public void processReferral(Referral r, String ehrUpdate) {
        queue.add(r);
        writeEmail(r);
        System.out.println("EHR Updated: " + ehrUpdate);
        appendReferralCSV(r);
    }

    private void writeEmail(Referral r) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("referral_emails.txt", true))) {
            bw.write("Referral ID: " + r.getRefID() + " | Patient: " + r.getPatientID() + 
                     " | Reason: " + r.getReason() + " | Date: " + r.getDate());
            bw.newLine(); bw.write("---"); bw.newLine();
        } catch (Exception e) {}
    }

    private void appendReferralCSV(Referral r) {
        List<String[]> data = new ArrayList<>();
        data.add(new String[]{String.valueOf(r.getRefID()), String.valueOf(r.getPatientID()),
                String.valueOf(r.getGpID()), String.valueOf(r.getSpecialistID()), r.getReason(), r.getDate().toString()});
        CSVUtil.writeCSV("referrals.csv", data, true);
    }
}