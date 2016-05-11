package coolcatmeow.org.welcomeanimation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class DisplayResumeActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_resume);


        System.out.println("INFO!!!!!!!!!!!!! INFO INFO INFO INFO INFO "+ getIntent().getStringExtra(MainActivity.USEREMAIL));

        String rawInfo = getIntent().getStringExtra(QrActivity.GETINFO);
        String[] info = rawInfo.split(";");

        /*
        	First_Name			- Text
            Last_Name			- Text
            Email				- Text
            Phone				- Text
            School_Name			- Text
            Major				- Text
            Company_Name		- Text
            Company_Position	- Text
         */

        System.out.println("WE'RE HERE! WE'RE HERE! WE'RE HERE! WE'RE HERE! WE'RE HERE! ");

        //From each Intent the string is extracted and set as the text for each TextView
        String receiveFirstName = info[0];
        TextView textfn = (TextView) findViewById(R.id.displayFirstName);
        if (textfn != null)
            textfn.setText(receiveFirstName);

        String receiveLastName = info[1];
        TextView textln = (TextView) findViewById(R.id.displayLastName);
        if (textln != null)
            textln.setText(receiveLastName);

        String receiveEmail = info[2];
        TextView textEmail = (TextView) findViewById(R.id.displayEmail);
        if (textEmail != null)
            textEmail.setText(receiveEmail);

        String receivePhone = info[3];
        TextView textPhone  = (TextView) findViewById(R.id.displayPhone);
        if (textPhone != null)
            textPhone.setText(receivePhone);

        String receiveSchoolName = info[4];
        TextView textview = (TextView) findViewById(R.id.displaySchoolName);
        if (textview != null)
            textview.setText(receiveSchoolName);

        String receiveMajor = info[5];
        TextView textMajor = (TextView) findViewById(R.id.displayMajor);
        if (textMajor != null)
            textMajor.setText(receiveMajor);

        String receiveCompany = info[6];
        TextView textCompany = (TextView) findViewById(R.id.displayCompany);
        if (textCompany != null)
            textCompany.setText(receiveCompany);

        String receivePosition = info[7];
        TextView textPosition = (TextView) findViewById(R.id.displayPosition);
        if (textPosition != null)
            textPosition.setText(receivePosition);

    }
}
