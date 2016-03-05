package com.amruthpillai.qrpro;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class ScanResultActivity extends AppCompatActivity {

    String contentString;
    TextView scanType, scanContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_result);

        Bundle extras = getIntent().getExtras();

        scanType = (TextView) findViewById(R.id.scan_type);
        scanContent = (TextView) findViewById(R.id.scan_content);

        contentString = extras.getString("SCAN_RESULT", "");

        scanType.setText(R.string.scan_plaintext);
        scanContent.setText(contentString);

        if (contentString.matches("[(http(s)?):\\/\\/(www\\.)?a-zA-Z0-9@:%._\\+~#=]{2,256}\\.[a-z]{2,6}\\b([-a-zA-Z0-9@:%_\\+.~#?&//=]*)")) {
            scanType.setText(R.string.scan_website);
            Toast.makeText(ScanResultActivity.this, "A website URL was scanned!", Toast.LENGTH_SHORT)
                    .show();
        } else if (contentString.contains("BEGINS:VCARD")) {
            scanType.setText(R.string.scan_contact);
            Toast.makeText(ScanResultActivity.this, "vCard was scanned!", Toast.LENGTH_SHORT)
                    .show();
        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClipboardManager clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("Scanned Content: ", contentString);
                clipboard.setPrimaryClip(clip);

                Snackbar.make(findViewById(android.R.id.content), R.string.scan_copy_clipboard, Snackbar.LENGTH_LONG)
                        .show();
            }
        });
    }

}
