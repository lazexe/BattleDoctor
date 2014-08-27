package com.lazexe.BattleDoctor;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;

public class LicenceAgreement {

    private static final String TAG = LicenceAgreement.class.getName();

    private AlertDialog.Builder builder;
    private final Activity activity;

    public LicenceAgreement(final Activity activity) {
        this.activity = activity;
        final View contentView = LayoutInflater.from(activity).inflate(R.layout.licence_agreement_view, null);
        builder = new AlertDialog.Builder(activity);
        builder.setPositiveButton(activity.getString(R.string.agree), new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                CheckBox showLicenceAgreementCheckBox = (CheckBox) contentView.findViewById(R.id.show_licence_agreement_checkbox);
                if (showLicenceAgreementCheckBox.isChecked()) {
                    SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(activity);
                    prefs.edit().putBoolean(PrefsActivity.LICENCE_KEY, false).commit();
                }
                dialog.cancel();
            }
        });
        builder.setNegativeButton(activity.getString(R.string.disagree), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                activity.finish();
            }
        });
        builder.setTitle(activity.getString(R.string.licence_agreement));
        builder.setIcon(activity.getResources().getDrawable(android.R.drawable.ic_dialog_alert));
        builder.setCancelable(false);
        builder.setView(contentView);
    }

    public void showLicenceAgreement() {
        boolean show = PreferenceManager.getDefaultSharedPreferences(activity).getBoolean(PrefsActivity.LICENCE_KEY, false);
        Log.d(TAG, String.valueOf(show));
        if (show) {
            AlertDialog dialog = builder.create();
            dialog.show();
        }
    }
}
