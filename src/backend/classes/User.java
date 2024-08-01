package backend.classes;

import java.io.File;
import java.util.Calendar;
import java.util.Date;

public abstract class User {

    protected String id;
    protected String userName;
    protected String lastName;
    protected String password;
    protected Date birthday;
    protected float balance;
    protected File profilePicture;
    protected Date registerDate;

    public User(String id, String userName, String lastName, String password, Date birthday, float balance, File profilePicture) {
        this.id = id;
        this.userName = userName;
        this.lastName = lastName;
        this.password = password;
        this.birthday = birthday;
        this.balance = balance;
        this.profilePicture = profilePicture;
        this.registerDate = new Date(); 
    }
    
    public Date getRegisterDate() {
    	return registerDate;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getPassword() {
        return password;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public File getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(File profilePicture) {
        this.profilePicture = profilePicture;
    }

    public int getAge() {
        Calendar birth = Calendar.getInstance();
        birth.setTime(this.birthday);
        Calendar today = Calendar.getInstance();

        int age = today.get(Calendar.YEAR) - birth.get(Calendar.YEAR);

        if (today.get(Calendar.DAY_OF_YEAR) < birth.get(Calendar.DAY_OF_YEAR)) {
            age--;
        }

        return age;
    }
}
