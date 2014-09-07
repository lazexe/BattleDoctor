package com.lazexe.BattleDoctor;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends Activity implements View.OnClickListener {

    public static final String WOUND_KEY = "title";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        initControls();
        showLicenceAgreement();
    }

    private void initControls() {
        ImageButton head = (ImageButton) findViewById(R.id.head);
        ImageButton leftShoulder = (ImageButton) findViewById(R.id.left_shoulder);
        ImageButton rightShoulder = (ImageButton) findViewById(R.id.right_shoulder);
        ImageButton chest = (ImageButton) findViewById(R.id.chest);
        ImageButton heart = (ImageButton) findViewById(R.id.heart);
        ImageButton rightLung = (ImageButton) findViewById(R.id.right_lung);
        ImageButton leftForearm = (ImageButton) findViewById(R.id.left_forearm);
        ImageButton rightForearm = (ImageButton) findViewById(R.id.right_forearm);
        ImageButton leftArm = (ImageButton) findViewById(R.id.left_arm);
        ImageButton rightArm = (ImageButton) findViewById(R.id.right_arm);
        ImageButton leftStomach = (ImageButton) findViewById(R.id.left_stomach);
        ImageButton rightStomach = (ImageButton) findViewById(R.id.right_stomach);
        ImageButton leftLeg = (ImageButton) findViewById(R.id.left_leg);
        ImageButton rightLeg = (ImageButton) findViewById(R.id.right_leg);
        ImageButton leftKnee = (ImageButton) findViewById(R.id.left_knee);
        ImageButton rightKnee = (ImageButton) findViewById(R.id.right_knee);
        ImageButton leftShin = (ImageButton) findViewById(R.id.left_shin);
        ImageButton rightShin = (ImageButton) findViewById(R.id.right_shin);
        ImageButton leftFoot = (ImageButton) findViewById(R.id.left_foot);
        ImageButton rightFoot = (ImageButton) findViewById(R.id.right_foot);
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
    public void onClick(View clickedView) {
        if (clickedView instanceof ImageButton) {
            ImageButton imageButton = (ImageButton) clickedView;
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

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(getString(R.string.exit));
        builder.setMessage(getString(R.string.rate_app_please));
        builder.setPositiveButton(getString(R.string.exit), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                finish();
            }
        });
        builder.setNeutralButton(getString(R.string.rate), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                openAppOnPlayMarket();
            }
        });
        builder.setNegativeButton(getString(android.R.string.cancel), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.create().show();
    }

    private void openAppOnPlayMarket() {
        Uri uri = Uri.parse("market://details?id=" + getPackageName());
        Intent playMarketIntent = new Intent(Intent.ACTION_VIEW, uri);
        try {
            startActivity(playMarketIntent);
        } catch (ActivityNotFoundException exception) {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://play.google.com/store/apps/details?id="
                    + getPackageName())));
        }
    }
}
