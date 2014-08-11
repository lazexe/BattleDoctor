package com.lazexe.BattleDoctor;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;


public class DoctorActivity extends Activity {

    private static final String TAG = DoctorActivity.class.getName();

    private ImageView descriptionImageView;
    private TextView firstHelpTextView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor);
        setTitle(getWound());
        initControls();
        try {
            showHelp(getDiagnostics());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String getDiagnostics() {
        String wound = getWound();
        if (wound != null) {
          if (wound.equals(getString(R.string.head)))
              return "head";
          if (wound.equals(getString(R.string.chest)) || wound.equals(getString(R.string.righ_lung))
              || wound.equals(getString(R.string.heart)))
              return "chest";
          if (wound.equals(getString(R.string.left_arm)) || wound.equals(getString(R.string.right_arm)) ||
              wound.equals(getString(R.string.left_forearm)) || wound.equals(getString(R.string.right_forearm)) ||
              wound.equals(getString(R.string.left_shoulder)) || wound.equals(getString(R.string.right_shoulder)))
              return "hand";
          if (wound.equals(getString(R.string.left_leg)) || wound.equals(getString(R.string.right_leg)) ||
              wound.equals(getString(R.string.left_foot)) || wound.equals(getString(R.string.right_foot)) ||
              wound.equals(getString(R.string.left_shin)) || wound.equals(getString(R.string.right_shin)) ||
              wound.equals(getString(R.string.left_knee)) || wound.equals(getString(R.string.right_knee)))
              return "leg";
          if (wound.equals(getString(R.string.left_stomach)) || wound.equals(getString(R.string.right_stomach)))
              return "stomach";
        }
        return null;
    }

    private String getWound() {
        return getIntent().getExtras().getString(MainActivity.WOUND_KEY);
    }

    private void initControls() {
        TextView woundTextView = (TextView) findViewById(R.id.wound_textview);
        woundTextView.setText(getWound());
        descriptionImageView = (ImageView) findViewById(R.id.description_imageview);
        firstHelpTextView = (TextView) findViewById(R.id.first_help_textview);
    }

    private void showHelp(String diagnostics) throws XmlPullParserException, IOException {
        descriptionImageView.setImageDrawable(getResources().getDrawable(findImageByDiagnostic(diagnostics)));
        final int NAME_ATTR_INDEX = 0;
        XmlPullParser xmlPullParser = getResources().getXml(R.xml.wounds);
        while (xmlPullParser.getEventType() != XmlPullParser.END_DOCUMENT) {
            if (xmlPullParser.getEventType() == XmlPullParser.START_TAG && xmlPullParser.getName().equals("wound")) {
                if (xmlPullParser.getAttributeValue(NAME_ATTR_INDEX).equals(diagnostics)) {
                    // TODO
                }
            }
            xmlPullParser.next();
        }

    }

    private int findImageByDiagnostic(String diagnostic) {
        if (diagnostic.equals("head"))
                return R.drawable.head;
        if (diagnostic.equals("hand"))
            return R.drawable.hand;
        if (diagnostic.equals("chest"))
            return R.drawable.chest;
        if (diagnostic.equals("leg"))
            return R.drawable.leg;
        if (diagnostic.equals("nech"))
            return R.drawable.neck;
        if (diagnostic.equals("stomach"))
            return R.drawable.stomach;
        return -1;
    }
}