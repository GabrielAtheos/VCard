package coolcatmeow.org.welcomeanimation;

import android.app.Application;

/**
 * Created by gabriel on 5/5/16.
 */
public class UserLoginEmail extends Application{
    private String USER_EMAIL = "";

    public String getUserEmail()
    {
        return USER_EMAIL;
    }

    public void setUserEmail(String email)
    {
        USER_EMAIL = email;
    }
}
