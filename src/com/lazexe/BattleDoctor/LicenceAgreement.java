package com.lazexe.BattleDoctor;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

public class LicenceAgreement {

    private AlertDialog.Builder builder;
    private final Activity activity;

    public LicenceAgreement(final Activity activity) {
        this.activity = activity;
        final View contentView = LayoutInflater.from(activity).inflate(R.layout.licence_agreement_view, null);
        final TextView licenceAgreementTextView = (TextView) contentView.findViewById(R.id.licence_agreement_textview);
        try {
            licenceAgreementTextView.setText(getLicenceAgreementFromXml(activity));
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        initAlertDialogBuilder(activity, contentView);
    }

    private void initAlertDialogBuilder(final Activity activity, final View contentView) {
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
        builder.setIcon(activity.getResources().getDrawable(R.drawable.ic_licence));
        builder.setCancelable(false);
        builder.setView(contentView);
    }

    public void showLicenceAgreement() {
        boolean show = PreferenceManager.getDefaultSharedPreferences(activity).getBoolean(PrefsActivity.LICENCE_KEY, true);
        if (show)
            builder.create().show();
    }

    public static String getLicenceAgreementFromXml(Activity activity) throws XmlPullParserException, IOException {
        StringBuilder stringBuilder = new StringBuilder();
        XmlPullParser xmlPullParser = activity.getResources().getXml(R.xml.points_of_agreement);
        int counter = 1;
        while (xmlPullParser.getEventType() != XmlPullParser.END_DOCUMENT) {
            switch (xmlPullParser.getEventType()) {
                case XmlPullParser.TEXT:
                    stringBuilder.append("\n")
                                 .append(String.valueOf(counter++))
                                 .append(". ")
                                 .append(xmlPullParser.getText());
                    break;
                    default:
                        break;
            }
            xmlPullParser.next();
        }
        return stringBuilder.toString();
    }
}
