package backend.classes;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Patient extends User {

    private float weigth;
    private float height;

    public Patient(String id, String userName, String lastName, String password, Date birthday, float balance, File profilePicture, float weigth, float height) {
        super(id, userName, lastName, password, birthday, balance, profilePicture);
        this.weigth = weigth;
        this.height = height;
    }


    public float getWeigth() {
        return weigth;
    }

    public void setWeigth(float weigth) {
        this.weigth = weigth;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public void setRegisterDate(Date time) {
        this.registerDate = time;
    }

    public String serializeToJson() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String birthday = dateFormat.format(this.getBirthday()); // Asume que getBirthday() es un método en User

        String profilePicturePath = (this.getProfilePicture() != null) ? this.getProfilePicture().getAbsolutePath() : "null";

        return "{"
                + "\"type\":\"Patient\","
                + "\"id\":\"" + getId() + "\","
                + "\"userName\":\"" + getUserName() + "\","
                + "\"lastName\":\"" + getLastName() + "\","
                + "\"password\":\"" + getPassword() + "\","
                + "\"birthday\":\"" + birthday + "\","
                + "\"balance\":" + getBalance() + ","
                + "\"profilePicture\":\"" + profilePicturePath + "\","
                + "\"weight\":" + weigth + ","
                + "\"height\":" + height
                + "}";
    }

    public static Patient deserializeFromJson(String json) {
        try {
            String id = extractJsonValue(json, "id");
            String userName = extractJsonValue(json, "userName");
            String lastName = extractJsonValue(json, "lastName");
            String password = extractJsonValue(json, "password");

            String birthdayString = extractJsonValue(json, "birthday");
            Date birthday = null;
            if (birthdayString != null && !birthdayString.isEmpty()) {
                try {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    birthday = sdf.parse(birthdayString);
                } catch (ParseException e) {
                    System.err.println("Error parsing birthday: " + e.getMessage());
                }
            }

            String balanceStr = extractJsonValue(json, "balance");
            float balance = parseFloatSafely(balanceStr);

            String profilePicturePath = extractJsonValue(json, "profilePicture");
            File profilePicture = (profilePicturePath != null && !profilePicturePath.equals("null")) ? new File(profilePicturePath) : null;

            String weightStr = extractJsonValue(json, "weight");
            float weight = parseFloatSafely(weightStr);

            String heightStr = extractJsonValue(json, "height");
            float height = parseFloatSafely(heightStr);

            return new Patient(id, userName, lastName, password, birthday, balance, profilePicture, weight, height);
        } catch (Exception e) {
            System.err.println("Error deserializing Patient: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    private static String extractJsonValue(String json, String key) {
        String pattern = "\"" + key + "\":";
        int startIndex = json.indexOf(pattern);
        if (startIndex == -1) {
            System.err.println("Key not found: " + key);
            return null;
        }
        startIndex += pattern.length();
        char delimiter = json.charAt(startIndex);
        int endIndex;
        if (delimiter == '"') {
            startIndex++;
            endIndex = json.indexOf('"', startIndex);
        } else {
            endIndex = json.indexOf(',', startIndex);
            if (endIndex == -1) {
                endIndex = json.indexOf('}', startIndex);
            }
        }
        if (endIndex == -1) {
            endIndex = json.length();
        }
        return json.substring(startIndex, endIndex).replace("\"", "").trim();
    }

    public static float parseFloatSafely(String value) {
        if (value != null && !value.isEmpty()) {
            try {
                return Float.parseFloat(value.replaceAll("[^\\d.]", ""));
            } catch (NumberFormatException e) {
                System.err.println("Invalid number format: " + value);
            }
        }
        return 0.0f;
    }
}