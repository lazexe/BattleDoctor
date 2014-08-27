package com.lazexe.BattleDoctor;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends Activity implements View.OnClickListener {

    private static final String TAG = MainActivity.class.getName();
    public static final String WOUND_KEY = "title";
    // top
    private ImageButton head;
    private ImageButton leftShoulder;
    private ImageButton rightShoulder;
    private ImageButton chest;
    // body
    private ImageButton heart;
    private ImageButton rightLung;
    private ImageButton leftForearm;
    private ImageButton rightForearm;
    private ImageButton leftArm;
    private ImageButton rightArm;
    private ImageButton leftStomach;
    private ImageButton rightStomach;
    // legs
    private ImageButton leftLeg;
    private ImageButton rightLeg;
    private ImageButton leftKnee;
    private ImageButton rightKnee;
    private ImageButton leftShin;
    private ImageButton rightShin;
    private ImageButton leftFoot;
    private ImageButton rightFoot;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        initControls();
        showLicenceAgreement();
    }

    private void initControls() {
        head = (ImageButton) findViewById(R.id.head);
        leftShoulder = (ImageButton) findViewById(R.id.left_shoulder);
        rightShoulder = (ImageButton) findViewById(R.id.right_shoulder);
        chest = (ImageButton) findViewById(R.id.chest);
        heart = (ImageButton) findViewById(R.id.heart);
        rightLung = (ImageButton) findViewById(R.id.right_lung);
        leftForearm = (ImageButton) findViewById(R.id.left_forearm);
        rightForearm = (ImageButton) findViewById(R.id.right_forearm);
        leftArm = (ImageButton) findViewById(R.id.left_arm);
        rightArm = (ImageButton) findViewById(R.id.right_arm);
        leftStomach = (ImageButton) findViewById(R.id.left_stomach);
        rightStomach = (ImageButton) findViewById(R.id.right_stomach);
        leftLeg = (ImageButton) findViewById(R.id.left_leg);
        rightLeg = (ImageButton) findViewById(R.id.right_leg);
        leftKnee = (ImageButton) findViewById(R.id.left_knee);
        rightKnee = (ImageButton) findViewById(R.id.right_knee);
        leftShin = (ImageButton) findViewById(R.id.left_shin);
        rightShin = (ImageButton) findViewById(R.id.right_shin);
        leftFoot = (ImageButton) findViewById(R.id.left_foot);
        rightFoot = (ImageButton) findViewById(R.id.right_foot);
        head.setOnClickListener(this);
        leftShoulder.setOnClickListener(this);
        rightShoulder.setOnClickListener(this);
        chest.setOnClickListener(this);
        heart.setOnClickListener(this);
        rightLung.setOnClickListener(this);
        leftForearm.setOnClickListener(this);
        rightForearm.setOnClickListener(this);
        leftArm.setOnClickListener(this);
        rightArm.setOnClickListener(this);
        leftStomach.setOnClickListener(this);
        rightStomach.setOnClickListener(this);
        leftLeg.setOnClickListener(this);
        rightLeg.setOnClickListener(this);
        leftKnee.setOnClickListener(this);
        rightKnee.setOnClickListener(this);
        leftShin.setOnClickListener(this);
        rightShin.setOnClickListener(this);
        leftFoot.setOnClickListener(this);
        rightFoot.setOnClickListener(this);
    }

    private void showLicenceAgreement() {
        new LicenceAgreement(this).showLicenceAgreement();
    }

    @Override
    public void onClick(View view) {
        if (view instanceof ImageButton) {
            ImageButton imageButton = (ImageButton) view;
            String contentDescription = String.valueOf(imageButton.getContentDescription());
            Intent doctorActivityIntent = new Intent(this, DoctorActivity.class);
            doctorActivityIntent.putExtra(WOUND_KEY, contentDescription);
            startActivity(doctorActivityIntent);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_activity_actions, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.settings_action) {
            Intent preferenceIntent = new Intent(this, PrefsActivity.class);
            startActivity(preferenceIntent);
            return true;
        }
        return false;
    }
}
