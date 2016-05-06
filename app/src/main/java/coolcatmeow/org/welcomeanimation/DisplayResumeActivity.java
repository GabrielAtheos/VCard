package coolcatmeow.org.welcomeanimation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DisplayResumeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_resume);

        //From each Intent the string is extracted and set as the text for each TextView
        String receiveFirstName = getIntent().getStringExtra(ResumeActivity.FIRST_NAME);
        TextView textfn = (TextView) findViewById(R.id.displayFirstName);
        textfn.setText(receiveFirstName);

        ///
        String receiveLastName = getIntent().getStringExtra(ResumeActivity.LAST_NAME);
        TextView textln = (TextView) findViewById(R.id.displayLastName);
        textln.setText(receiveLastName);

        String receiveEmail = getIntent().getStringExtra(ResumeActivity.EMAIL);
        TextView textEmail = (TextView) findViewById(R.id.displayEmail);
        textEmail.setText(receiveEmail);

        String receivePhone = getIntent().getStringExtra(ResumeActivity.PHONE);
        TextView textPhone  = (TextView) findViewById(R.id.displayPhone);
        textPhone.setText(receivePhone);

        String receiveSchoolName = getIntent().getStringExtra(ResumeActivityTwo.SCHOOL_NAME);
        TextView textview = (TextView) findViewById(R.id.displaySchoolName);
        textview.setText(receiveSchoolName);

        String receiveMajor = getIntent().getStringExtra(ResumeActivity.MAJOR);
        TextView textMajor = (TextView) findViewById(R.id.displayMajor);
        textMajor.setText(receiveMajor);

        String receiveCompany = getIntent().getStringExtra(ResumeActivity.COMPANY_NAME);
        TextView textCompany = (TextView) findViewById(R.id.displayCompany);
        textCompany.setText(receiveCompany);

        String receivePosition = getIntent().getStringExtra(ResumeActivity.COMPANY_POSITION);
        TextView textPosition = (TextView) findViewById(R.id.displayPosition);
        textPosition.setText(receivePosition);

    }
}
