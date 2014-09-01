package com.lazexe.BattleDoctor;

import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;
import android.view.MenuItem;
import android.widget.Toast;

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

    class PrefFragment extends PreferenceFragment implements Preference.OnPreferenceClickListener {

        private static final String MAIL_ME_PREF_KEY = "mail_me";
        private static final String DISPLAY_LICENCE_PREF_KEY = "display_licence";
        private static final String RATE_APP_PREFERENCE = "rate_app";

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.preferences);
            Preference mailPrefernce = findPreference(MAIL_ME_PREF_KEY);
            mailPrefernce.setOnPreferenceClickListener(this);
            Preference displayLicencePreference = findPreference(DISPLAY_LICENCE_PREF_KEY);
            displayLicencePreference.setOnPreferenceClickListener(this);
            Preference rateAppPreference = findPreference(RATE_APP_PREFERENCE);
            rateAppPreference.setOnPreferenceClickListener(this);
        }

        @Override
        public boolean onPreferenceClick(Preference preference) {
            if (preference.getKey().equals(MAIL_ME_PREF_KEY)) {
                startMailApplication();
                return true;
            }
            if (preference.getKey().equals(DISPLAY_LICENCE_PREF_KEY)) {
                showLicenceDialog();
                return true;
            }
            if (preference.getKey().equals(RATE_APP_PREFERENCE)) {
                Uri uri = Uri.parse("market://details?id=" + getActivity().getPackageName());
                Intent playMarketIntent = new Intent(Intent.ACTION_VIEW, uri);
                try {
                    startActivity(playMarketIntent);
                } catch (ActivityNotFoundException exception) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://play.google.com/store/apps/details?id="
                            + getActivity().getPackageName())));
                }
                return true;
            }
            return false;
        }

        private void startMailApplication() {
            Intent mailIntent = new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:lazexek21@gmail.com"));
            mailIntent.putExtra(Intent.EXTRA_SUBJECT, "Battle doctor");
            startActivity(Intent.createChooser(mailIntent, "Send email"));
        }

        private void showLicenceDialog() {
            String licenceAgreementText = "";
            try {
                licenceAgreementText = LicenceAgreement.getLicenceAgreementFromXml(getActivity());
            } catch (Exception exception) {
                exception.printStackTrace();
            }
            AlertDialog.Builder dialogBuidler = new AlertDialog.Builder(getActivity());
            dialogBuidler.setTitle(getString(R.string.licence_agreement));
            dialogBuidler.setMessage(licenceAgreementText);
            dialogBuidler.setIcon(getResources().getDrawable(R.drawable.ic_licence));
            dialogBuidler.setCancelable(false);
            dialogBuidler.setPositiveButton(getString(android.R.string.ok), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.cancel();
                }
            });
            AlertDialog dialog = dialogBuidler.create();
            dialog.show();
        }
    }
}
