package backend.classes;
import backend.enums.Priority;

public class Disease {
    private String id;
    private String name;
    private boolean isVaccinable;
    private Priority priority;

    public Disease(String id, String name, boolean isVaccinable, Priority priority) {
        this.id = id;
        this.name = name;
        this.isVaccinable = isVaccinable;
        this.priority = priority;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isVaccinable() {
        return isVaccinable;
    }

    public void setVaccinable(boolean isVaccinable) {
        this.isVaccinable = isVaccinable;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public String serializeToJson() {
        // Convertir el enum Priority a su representación en cadena
        String priorityStr = (priority != null) ? priority.name() : "null";

        return "{"
                + "\"id\":\"" + id + "\","
                + "\"name\":\"" + name + "\","
                + "\"isVaccinable\":" + isVaccinable + ","
                + "\"priority\":\"" + priorityStr + "\""
                + "}";
    }
}
