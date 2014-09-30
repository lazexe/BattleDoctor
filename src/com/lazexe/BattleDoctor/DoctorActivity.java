package com.lazexe.BattleDoctor;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
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
            String diagnostics = getDiagnostics();
            showHelp(diagnostics);
        } catch (Exception exception) {
            exception.printStackTrace();
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
        descriptionImageView = (ImageView) findViewById(R.id.description_imageview);
        firstHelpTextView = (TextView) findViewById(R.id.first_help_textview);
        Typeface robotoRegularTypeface = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Light.ttf");
        firstHelpTextView.setTypeface(robotoRegularTypeface);
        if (getActionBar() != null) {
            getActionBar().setHomeButtonEnabled(true);
            getActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    private void showHelp(String diagnostics) throws XmlPullParserException, IOException {
        descriptionImageView.setImageDrawable(getResources().getDrawable(findImageIdByDiagnostic(diagnostics)));
        final int NAME_ATTR_INDEX = 0;
        XmlPullParser xmlPullParser = getResources().getXml(R.xml.wounds);
        while (xmlPullParser.getEventType() != XmlPullParser.END_DOCUMENT) {
            if (xmlPullParser.getEventType() == XmlPullParser.START_TAG && xmlPullParser.getName().equals("wound")) {
                if (xmlPullParser.getAttributeValue(NAME_ATTR_INDEX).equals(diagnostics)) {
                    xmlPullParser.next();
                    int counter = 1;
                    while (true) {
                        if (xmlPullParser.getEventType() == XmlPullParser.TEXT) {
                            firstHelpTextView.append(String.valueOf(counter) + ". " + xmlPullParser.getText() + "\n");
                            counter++;
                        }
                        if (xmlPullParser.getEventType() == XmlPullParser.END_TAG && xmlPullParser.getName().equals("wound"))
                            return;
                        xmlPullParser.next();
                    }
                }
            }
            xmlPullParser.next();
        }

    }

    private int findImageIdByDiagnostic(String diagnostic) {
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
        Log.d(TAG, "No such diagnostic!!!");
        return -1;
    }
}