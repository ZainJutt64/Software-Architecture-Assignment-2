package healthcare.model;
public class HealthcareFacility {
    private int facilityID;
    private String name;
    private String address;

    public HealthcareFacility(int facilityID, String name, String address) {
        this.facilityID = facilityID;
        this.name = name;
        this.address = address;
    }

    public int getFacilityID() { return facilityID; }
    public String getName() { return name; }
}