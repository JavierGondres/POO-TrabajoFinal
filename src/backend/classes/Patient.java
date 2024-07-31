package backend.classes;

import java.io.File;
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
}
