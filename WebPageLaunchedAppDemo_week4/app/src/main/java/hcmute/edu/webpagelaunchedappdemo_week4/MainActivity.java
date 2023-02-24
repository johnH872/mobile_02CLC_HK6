package hcmute.edu.webpagelaunchedappdemo_week4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent receivedIntent = getIntent();
        Uri uri = receivedIntent.getData();
        URL webPageURL = null;

        if (uri != null) {
            try {
                webPageURL = new URL(uri.getScheme(), uri.getHost(), uri.getPath());
            } catch (MalformedURLException e) {
                Toast.makeText(this, "Intent exepction occured", Toast.LENGTH_LONG).show();
            }

            WebView webView = (WebView) findViewById(R.id.webView);
            webView.getSettings().setJavaScriptEnabled(true);
            webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
            webView.setWebViewClient(new WebViewClient());
            webView.loadUrl(webPageURL.toString());
        }
    }
}