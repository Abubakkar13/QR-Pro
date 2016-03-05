package com.amruthpillai.qrpro;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class MainActivity extends AppCompatActivity {

    TextView textView_scan, textView_generate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView_scan = (TextView) findViewById(R.id.text_scan);
        textView_generate = (TextView) findViewById(R.id.text_generate);

        Typeface typeface_Exo2ExtraLight = Typeface.createFromAsset(getAssets(), "fonts/exo2-extralight.ttf");
        textView_scan.setTypeface(typeface_Exo2ExtraLight);
        textView_generate.setTypeface(typeface_Exo2ExtraLight);

    }

    public void clickOnScan(View view) {
        new IntentIntegrator(this).initiateScan();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() == null) {
                Snackbar.make(findViewById(android.R.id.content), R.string.main_scan_cancel, Snackbar.LENGTH_LONG)
                        .show();
            } else {
                Intent intent = new Intent(MainActivity.this, ScanResultActivity.class);
                intent.putExtra("SCAN_RESULT", result.getContents());
                startActivity(intent);
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    public void clickOnGenerate(View view) {
        Intent i = new Intent(getApplicationContext(), GenerateActivity.class);
        startActivity(i);
    }

}
