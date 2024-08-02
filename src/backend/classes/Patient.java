package backend.classes;

import java.io.File;
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
                + "\"id\":\"" + getId() + "\","
                + "\"userName\":\"" + getUserName() + "\","
                + "\"lastName\":\"" + getLastName() + "\","
                + "\"password\":\"" + getPassword() + "\","
                + "\"birthday\":\"" + birthday + "\","
                + "\"balance\":" + getBalance() + ","
                + "\"profilePicture\":\"" + profilePicturePath + "\","
                + "\"weigth\":" + weigth + ","
                + "\"height\":" + height
                + "}";
    }
}
