package com.lazexe.BattleDoctor;

import android.app.Activity;
import android.os.Bundle;


public class DoctorActivity extends Activity {

    private String wound;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor);
        wound = getWound();
        setTitle(wound);
    }

    public String getWound() {
        return getIntent().getExtras().getString(MainActivity.WOUND_KEY);
    }
}