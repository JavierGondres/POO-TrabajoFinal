package backend.classes;

public class Vaccine {
    private String id;
    private String name;
    private String diseaseId;
    private int minAge;
    private int maxAge;

    public Vaccine(String id, String name, String diseaseId, int minAge, int maxAge) {
        this.id = id;
        this.name = name;
        this.diseaseId = diseaseId;
        this.minAge = minAge;
        this.maxAge = maxAge;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDiseaseId() {
        return diseaseId;
    }

    public void setDisease(String diseaseId) {
        this.diseaseId = diseaseId;
    }

    public int getMinAge() {
        return minAge;
    }

    public void setMinAge(int minAge) {
        this.minAge = minAge;
    }

    public int getMaxAge() {
        return maxAge;
    }

    public void setMaxAge(int maxAge) {
        this.maxAge = maxAge;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String serializeToJson() {
        return "{"
                + "\"id\":\"" + id + "\","
                + "\"name\":\"" + name + "\","
                + "\"diseaseId\":\"" + diseaseId + "\","
                + "\"minAge\":" + minAge + ","
                + "\"maxAge\":" + maxAge
                + "}";
    }
}
