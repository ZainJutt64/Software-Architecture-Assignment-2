package healthcare.controller;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import healthcare.model.Referral;

import healthcare.util.CSVUtil;

public class ReferralController {
    private List<Referral> referrals = new ArrayList<>();
    private int nextID = 1;

    public void loadReferrals() {
        List<String[]> data = CSVUtil.readCSV("referrals.csv");
        if (data.isEmpty()) return;
        for (String[] row : data.subList(1, data.size())) {
            try {
                Referral r = new Referral(
                    Integer.parseInt(row[0]),
                    Integer.parseInt(row[1]),
                    Integer.parseInt(row[2]),
                    Integer.parseInt(row[3]),
                    row[4],
                    LocalDate.parse(row[5])
                );
                referrals.add(r);
                nextID = Math.max(nextID, r.getRefID() + 1);
            } catch (Exception e) {}
        }
    }

    public void makeReferral(int patientID, int gpID, int specialistID, String reason, LocalDate date, String ehrUpdate) {
        Referral r = new Referral(nextID++, patientID, gpID, specialistID, reason, date);
        referrals.add(r);
        healthcare.service.ReferralManager.getInstance().processReferral(r, ehrUpdate);
    }

    public List<Referral> getReferrals() {
        return referrals;
    }
}