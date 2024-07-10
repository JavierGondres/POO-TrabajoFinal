package backend.classes;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class Record {
    private String symptoms;
    private String description;
    private ArrayList<Disease> suffering;
    private ArrayList<Disease> DiseaseHistory;
    private ArrayList<Vaccine> vaccines;
    private float weight;
    private float height;
    private Date lastModification;

    public Record(String symptoms, String description, ArrayList<Disease> suffering, ArrayList<Disease> DiseaseHistory, ArrayList<Vaccine> vaccines, float weight, float height, Date lastModification) {
        this.symptoms = symptoms;
        this.description = description;
        this.suffering = suffering;
        this.DiseaseHistory = DiseaseHistory;
        this.vaccines = vaccines;
        this.weight = weight;
        this.height = height;
        this.lastModification = lastModification;
    }

    public String getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(String symptoms) {
        this.symptoms = symptoms;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<Disease> getSuffering() {
        return suffering;
    }

    public void setSuffering(ArrayList<Disease> suffering) {
        this.suffering = suffering;
    }

    public ArrayList<Disease> getDiseaseHistory() {
        return DiseaseHistory;
    }

    public void setDiseaseHistory(ArrayList<Disease> DiseaseHistory) {
        this.DiseaseHistory = DiseaseHistory;
    }

    public ArrayList<Vaccine> getVaccines() {
        return vaccines;
    }

    public void setVaccines(ArrayList<Vaccine> vaccines) {
        this.vaccines = vaccines;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public Date getLastModification() {
        return lastModification;
    }

    public void setLastModification(Date lastModification) {
        this.lastModification = lastModification;
    }

    public void addDisease(Disease disease) {
        this.suffering.add(disease);
    }

    public void addDiseaseHistory(Disease disease) {
        this.DiseaseHistory.add(disease);
    }

    public void addVaccine(Vaccine vaccine) {
        this.vaccines.add(vaccine);
    }

    public void removeDisease(Disease disease) {
        this.suffering.remove(disease);
    }

    public void removeDiseaseHistory(Disease disease) {
        this.DiseaseHistory.remove(disease);
    }

    public void removeVaccine(Vaccine vaccine) {
        this.vaccines.remove(vaccine);
    }

    public void updateLastModification() {
        this.lastModification = new Date();
    }

    HashMap<String, Object> toHashMap() {
        HashMap<String, Object> record = new HashMap<>();
        record.put("symptoms", this.symptoms);
        record.put("description", this.description);
        record.put("suffering", this.suffering);
        record.put("DiseaseHistory", this.DiseaseHistory);
        record.put("vaccines", this.vaccines);
        record.put("weight", this.weight);
        record.put("height", this.height);
        record.put("lastModification", this.lastModification);
        return record;
    }

}
