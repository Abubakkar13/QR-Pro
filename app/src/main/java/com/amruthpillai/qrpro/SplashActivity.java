package com.amruthpillai.qrpro;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class SplashActivity extends AppCompatActivity {

    TextView splashAppName, splashAppVersion, splashAppAuthor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        splashAppName = (TextView) findViewById(R.id.splash_app_name);
        splashAppVersion = (TextView) findViewById(R.id.splash_app_version);
        splashAppAuthor = (TextView) findViewById(R.id.splash_app_author);

        Typeface typeface_Exo2ExtraLight = Typeface.createFromAsset(getAssets(), "fonts/exo2-extralight.ttf");
        splashAppName.setTypeface(typeface_Exo2ExtraLight);
        splashAppVersion.setTypeface(typeface_Exo2ExtraLight);
        splashAppAuthor.setTypeface(typeface_Exo2ExtraLight);

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);

                finish();
            }

        }, 2000);

    }
}
