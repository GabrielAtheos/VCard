package coolcatmeow.org.welcomeanimation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;



public class ResumeActivityTwo extends AppCompatActivity {
    public final static String SCHOOL_NAME = "coolcatmeow.org.welcomeanimation.SCHOOL_NAME";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resume_activity_two);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Pulls the string from the "SCHOOL Name" Edit text//
        EditText edt = (EditText) findViewById(R.id.editTextSchoolName);
        final String schoolName = edt.getText().toString();


        Button goToMain = (Button) findViewById(R.id.buttonGoToDisplay);
        goToMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(ResumeActivityTwo.this, DisplayResumeActivity.class);

                intent.putExtra(SCHOOL_NAME, schoolName);
                startActivity(intent);
            }
        });
    }

}
