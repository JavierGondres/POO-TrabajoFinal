package backend.classes;

public class Vaccine {
    private String id;
    private Disease disease;
    private int minAge;
    private int maxAge;

    public Vaccine(String id, Disease disease, int minAge, int maxAge) {
        this.id = id;
        this.disease = disease;
        this.minAge = minAge;
        this.maxAge = maxAge;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Disease getDisease() {
        return disease;
    }

    public void setDisease(Disease disease) {
        this.disease = disease;
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
}
