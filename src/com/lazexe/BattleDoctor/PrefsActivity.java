package com.lazexe.BattleDoctor;

import android.os.Bundle;
import android.preference.*;
import android.view.MenuItem;

public class PrefsActivity extends PreferenceActivity {

    public static final String LICENCE_KEY = "licence_key";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(getString(R.string.preferences));
        getFragmentManager().beginTransaction().replace(android.R.id.content, new PrefFragment()).commit();
        if (getActionBar() != null) {
            getActionBar().setHomeButtonEnabled(true);
            getActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            super.onBackPressed();
            return true;
        }
        return false;
    }

    class PrefFragment extends PreferenceFragment {
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.preferences);
        }

    }
}
