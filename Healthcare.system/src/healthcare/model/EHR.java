package healthcare.model;
public class EHR {
    private int ehrID;
    private int patientID;
    private String records;

    public EHR(int ehrID, int patientID, String records) {
        this.ehrID = ehrID;
        this.patientID = patientID;
        this.records = records == null ? "" : records;
    }

    public void update(String newRecord) {
        this.records += "\n" + newRecord;
    }

    public String getRecords() { return records; }
}