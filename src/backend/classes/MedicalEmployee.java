package backend.classes;

import backend.enums.QueryTime;
import backend.enums.Specialty;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Date;
import java.time.LocalTime;
import java.util.Locale;

public class MedicalEmployee extends Employee {
    private ArrayList<Specialty> specialities;
    private QueryTime queryTime;
    private float queryPrice;

    public MedicalEmployee(String id, String userName, String lastName, String password, Date birthday, float balance,
                           ArrayList<Specialty> specialities, LocalTime doctorShiftStart, LocalTime doctorShiftEnd, File profilePicture, QueryTime queryTime, float queryPrice) {
        super(id, userName, lastName, password, birthday, balance, doctorShiftStart, doctorShiftEnd, profilePicture);
        this.specialities = specialities;
        this.queryTime = queryTime;
        this.queryPrice = queryPrice;
    }

    public ArrayList<Specialty> getSpecialities() {
        return specialities;
    }

    public void setSpecialities(ArrayList<Specialty> specialities) {
        this.specialities = specialities;
    }

    public float calculateSalary() {
        return getDefaultSalary() * calculateWorkSchedule();
    }

    @Override
    public float getDefaultSalary() {
        return 0;
    }

    public QueryTime getQueryTime() {
        return queryTime;
    }

    public void setQueryTime(QueryTime queryTime) {
        this.queryTime = queryTime;
    }

    public int getIntervalMinutesOfQueryTime() {
        if (queryTime == QueryTime.THIRTY_MINUTES) {
            return 30;
        } else if (queryTime == QueryTime.SIXTY_MINUTES) {
            return 60;
        } else if (queryTime == QueryTime.NINETY_MINUTES) {
            return 90;
        } else if (queryTime == QueryTime.ONE_HUNDRED_TWENTY_MINUTES) {
            return 120;
        } else {
            throw new IllegalArgumentException("Unexpected value: " + queryTime);
        }
    }
    
    public ArrayList<LocalTime> getRangeOfQueryTime() {
        ArrayList<LocalTime> rangeOfTimes = new ArrayList<>();
        int intervalMinutes = getIntervalMinutesOfQueryTime();

        LocalTime currentTime = shiftStart;
        LocalTime endTime = shiftEnd;

        while (currentTime.plusMinutes(intervalMinutes).isBefore(endTime) || currentTime.plusMinutes(intervalMinutes).equals(endTime)) {
            rangeOfTimes.add(currentTime);
            currentTime = currentTime.plusMinutes(intervalMinutes);
        }

        return rangeOfTimes;
    }

    public float getQueryPrice() {
        return queryPrice;
    }

    public void setQueryPrice(float queryPrice) {
        this.queryPrice = queryPrice;
    }

    public String serializeToJson() {
        return "{" +
                "\"type\":\"Medical Employee\","+
                "\"id\": \"" + this.getId() + "\"," +
                "\"userName\": \"" + this.getUserName() + "\"," +
                "\"lastName\": \"" + this.getLastName() + "\"," +
                "\"password\": \"" + this.getPassword() + "\"," +
                "\"birthday\": \"" + new SimpleDateFormat("yyyy-MM-dd").format(this.getBirthday()) + "\"," +
                "\"balance\": " + this.getBalance() + "," +
                "\"specialities\": " + this.specialities.toString() + "," +
                "\"shiftStart\": \"" + this.getShiftStart() + "\"," +
                "\"shiftEnd\": \"" + this.getShiftEnd() + "\"," +
                "\"profilePicture\": \"" + (this.getProfilePicture() != null ? this.getProfilePicture().getAbsolutePath() : "null") + "\"," +
                "\"queryTime\": \"" + (this.queryTime != null ? this.queryTime.toString() : "null") + "\"," +
                "\"queryPrice\": " + this.queryPrice +
                "}";
    }

    public static MedicalEmployee deserializeFromJson(String json) {
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

            String balanceString = extractJsonValue(json, "balance");
            float balance = parseFloatSafely(balanceString);

            String queryPriceString = extractJsonValue(json, "queryPrice");
            float queryPrice = parseFloatSafely(queryPriceString);

            ArrayList<Specialty> specialities = extractSpecialities(json);

            String shiftStartString = extractJsonValue(json, "shiftStart");
            String shiftEndString = extractJsonValue(json, "shiftEnd");

            LocalTime shiftStart = null;
            LocalTime shiftEnd = null;
            try {
                if (shiftStartString != null && !shiftStartString.isEmpty()) {
                    shiftStart = LocalTime.parse(shiftStartString);
                }
                if (shiftEndString != null && !shiftEndString.isEmpty()) {
                    shiftEnd = LocalTime.parse(shiftEndString);
                }
            } catch (DateTimeParseException e) {
                System.err.println("Error parsing shift times: " + e.getMessage());
            }

            String profilePicturePath = extractJsonValue(json, "profilePicture");
            File profilePicture = (profilePicturePath != null && !profilePicturePath.equals("null")) ?
                    new File(profilePicturePath) : null;

            String queryTimeString = extractJsonValue(json, "queryTime");
            QueryTime queryTime = null;
            if (queryTimeString != null && !queryTimeString.isEmpty()) {
                try {
                    queryTime = QueryTime.valueOf(queryTimeString);
                } catch (IllegalArgumentException e) {
                    System.err.println("Invalid QueryTime: " + e.getMessage());
                }
            }

            return new MedicalEmployee(id, userName, lastName, password, birthday, balance, specialities, shiftStart, shiftEnd, profilePicture, queryTime, queryPrice);
        } catch (Exception e) {
            System.err.println("Error deserializing MedicalEmployee: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    private static String extractJsonValue(String json, String key) {
        String pattern = "\"" + key + "\":";
        int startIndex = json.indexOf(pattern);
        if (startIndex == -1) {
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
            return json.substring(startIndex).trim();
        }
        return json.substring(startIndex, endIndex).replace("\"", "").trim();
    }

    public static ArrayList<Specialty> extractSpecialities(String json) {
        ArrayList<Specialty> specialities = new ArrayList<>();
        String specialitiesString = extractJsonValue(json, "specialities");

        if (specialitiesString == null || specialitiesString.trim().isEmpty()) {
            return specialities; // Return empty list if no specialities found
        }

        specialitiesString = specialitiesString.replaceAll("[\\[\\]\"]", "").trim();

        if (specialitiesString.isEmpty()) {
            return specialities;
        }

        String[] specialitiesStrings = specialitiesString.split(",");
        for (String specialityString : specialitiesStrings) {
            String trimmedSpeciality = specialityString.trim();
            if (!trimmedSpeciality.isEmpty()) {
                try {
                    Specialty speciality = Specialty.valueOf(trimmedSpeciality.toUpperCase());
                    specialities.add(speciality);
                } catch (IllegalArgumentException e) {
                    System.err.println("Unknown specialty: " + trimmedSpeciality);
                }
            }
        }
        return specialities;
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
