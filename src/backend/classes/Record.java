package backend.classes;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class Record {
    private String patientId;
    private String symptoms;
    private String description;
    private ArrayList<Disease> DiseaseHistory;
    private ArrayList<Vaccine> vaccines;
    private float weight;
    private float height;
    private Date lastModification;

    public Record(String patientId, String symptoms, String description, ArrayList<Disease> DiseaseHistory, ArrayList<Vaccine> vaccines, float weight, float height, Date lastModification) {
       this.patientId = patientId;
        this.symptoms = symptoms;
        this.description = description;
        this.DiseaseHistory = DiseaseHistory;
        this.vaccines = vaccines;
        this.weight = weight;
        this.height = height;
        this.lastModification = lastModification;
    }

    public Record(Record patientRecord) {
        this.patientId = patientRecord.patientId;
        this.symptoms = patientRecord.symptoms;
        this.description = patientRecord.description;
        this.DiseaseHistory = new ArrayList<>(patientRecord.DiseaseHistory);
        this.vaccines = new ArrayList<>(patientRecord.vaccines);
        this.weight = patientRecord.weight;
        this.height = patientRecord.height;
        this.lastModification = patientRecord.lastModification;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
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

    public void addDiseaseHistory(Disease disease) {
        this.DiseaseHistory.add(disease);
    }

    public void addVaccine(Vaccine vaccine) {
        this.vaccines.add(vaccine);
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
        record.put("DiseaseHistory", this.DiseaseHistory);
        record.put("vaccines", this.vaccines);
        record.put("weight", this.weight);
        record.put("height", this.height);
        record.put("lastModification", this.lastModification);
        return record;
    }

    public String serializeToJson() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        // Serializar la lista de enfermedades
        StringBuilder diseasesJson = new StringBuilder("[");
        for (Disease disease : DiseaseHistory) {
            diseasesJson.append(disease.serializeToJson()).append(",");
        }
        if (!DiseaseHistory.isEmpty()) diseasesJson.setLength(diseasesJson.length() - 1); // Eliminar última coma
        diseasesJson.append("]");

        // Serializar la lista de vacunas
        StringBuilder vaccinesJson = new StringBuilder("[");
        for (Vaccine vaccine : vaccines) {
            vaccinesJson.append(vaccine.serializeToJson()).append(",");
        }
        if (!vaccines.isEmpty()) vaccinesJson.setLength(vaccinesJson.length() - 1); // Eliminar última coma
        vaccinesJson.append("]");

        String lastModificationDate = (lastModification != null) ? dateFormat.format(lastModification) : "null";

        return "{"
                + "Record: "
                + "\"patientId\":\"" + patientId + "\","
                + "\"symptoms\":\"" + symptoms + "\","
                + "\"description\":\"" + description + "\","
                + "\"DiseaseHistory\":" + diseasesJson.toString() + ","
                + "\"vaccines\":" + vaccinesJson.toString() + ","
                + "\"weight\":" + weight + ","
                + "\"height\":" + height + ","
                + "\"lastModification\":\"" + lastModificationDate + "\""
                + "}";
    }
}
