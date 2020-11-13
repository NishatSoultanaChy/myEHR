package com.nishatsultana.myehr.Patient;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;


import com.nishatsultana.myehr.R;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class ViewPDFActivity extends AppCompatActivity {

    WebView pdfView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_p_d_f);

        pdfView = findViewById(R.id.viewPDF);
        pdfView.getSettings().setJavaScriptEnabled(true);//****



        String filename = getIntent().getStringExtra("filename");
        String fileurl = getIntent().getStringExtra("fileurl");

        final ProgressDialog pd = new ProgressDialog(this);
        pd.setTitle(filename);
        pd.setMessage("Opening...");

        pdfView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                pd.show();
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                pd.dismiss();
            }
        });

        String url ="";
        try {
            url = URLEncoder.encode(fileurl,"UTF-8");
        }catch (UnsupportedEncodingException e){
            e.printStackTrace();
        }
        pdfView.loadUrl("https://docs.google.com/viewer?embedded = true&url = "+ url);
    }
}