package com.nukul4r.mucwetter;

import android.app.Activity;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;

import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends Activity {
    private final String TAG = getClass().toString();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        WebView webView = ((WebView) findViewById(R.id.webView));
        webView.setBackgroundColor(Color.DKGRAY);
        webView.loadUrl("file:///android_asset/index.html");

        registerButton(R.id.btnT850Nds);
        registerButton(R.id.btnMm);
        registerButton(R.id.btnMucFh);
        registerButton(R.id.btnNsr);
        registerButton(R.id.btnNsrB);
        registerButton(R.id.btnWrf);
        registerButton(R.id.btnSun);
        registerButton(R.id.btnIce);
    }

    public void registerButton(final int id) {
        ((Button) findViewById(id)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                String htmlString = "";
                try {
                    htmlString = getHtmlString(id);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                ((WebView) findViewById(R.id.webView)).loadData(htmlString, "text/html", "UTF-8");
            }
        });
    }

    public String getHtmlString(int id) throws IOException {
        InputStream is = getAssets().open("imageContainer.html");
        byte[] buffer = new byte[is.available()];
        is.read(buffer);
        is.close();
        String s = new String(buffer);
        s = s.replace("url", findViewById(id).getTag().toString());
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            s = s.replace("height", "width");
        }
        return s;
    }
}
