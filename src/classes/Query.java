package classes;

import java.util.Date;

public class Query {
    private String id;
    private String patientID;
    private String doctorID;
    private float fee;
    private Date date;
    private Record record;
    private boolean active;

    public Query(String id, String patientID, String doctorID, float fee, Date date, Record record, boolean active) {
        this.id = id;
        this.patientID = patientID;
        this.doctorID = doctorID;
        this.fee = fee;
        this.date = date;
        this.record = record;
        this.active = active;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPatientID() {
        return patientID;
    }

    public void setPatientID(String patientID) {
        this.patientID = patientID;
    }

    public String getDoctorID() {
        return doctorID;
    }

    public void setDoctorID(String doctorID) {
        this.doctorID = doctorID;
    }

    public float getFee() {
        return fee;
    }

    public void setFee(float fee) {
        this.fee = fee;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Record getRecord() {
        return record;
    }

    public void setRecord(Record record) {
        this.record = record;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void postponeDate (Date newDate) {
        this.date = newDate;
    }

    public void cancelConsultation() {
        this.active = false;
    }
}
